package whustore.dao;

import whustore.data.ProductData;
import whustore.model.Order;
import whustore.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderDao {

    Connection conn;

    /**
     * 获取用户的历史订单
     *
     * @param userID
     * @return
     */
    public List<Order> getOrderlist(int userID) {
        List<Order> orderlist = new ArrayList<Order>();
        List<Integer> orderidList = this.getOrderID(userID);
        String sql = "SELECT * FROM orderInfo WHERE idorder=?";
        int amount = 0;
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            if (conn == null || conn.isClosed())
                conn = DBConnector.getDBConn();
            ps = conn.prepareStatement(sql);
            for (Integer i : orderidList) {
                ps.setInt(1, i);
                rs = ps.executeQuery();
                Order order = new Order();
                HashMap<Product, Integer> items = new HashMap<Product, Integer>();
                boolean statusAdded = false;
                while (rs.next()) {
                    if (!statusAdded) {
                        order.setPhone(rs.getString("phone"));
                        order.setStatus(rs.getString("ostatus"));
                        order.setName(rs.getString("name"));
                        order.setAddress(rs.getString("address"));
                        statusAdded = true;
                    }
                    Product current = new Product();
                    int id = rs.getInt("idproduct");
                    current = ProductData.getProductByID(id);
                    amount = rs.getInt("amount");
                    items.put(current, amount);
                }
                order.setIdOrder(i);
                order.setIduser(userID);
                order.setItems(items);
                orderlist.add(order);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭数据库连接
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return orderlist;
    }

    /**
     * 添加Order
     *
     * @param order  order对象
     * @param idcart 购物车号码
     * @return 是否成功
     */
    public boolean addOrder(Order order, int idcart) {
        PreparedStatement ps = null;
        ProductDao pd = new ProductDao();
        CartDao cd = new CartDao();
        int lastquantity = 0;
        String insert_order = "INSERT INTO orders(idorder, iduser, phone, address, name) values(?,?,?,?,?)";
        String insert_orderitem = "INSERT INTO orderitem(idorder,idproduct,amount,price) values(?,?,?,?)";
        String changequantity = "UPDATE product SET quantity = ? WHERE idproduct = ?";
        try {
            if (conn == null || conn.isClosed())
                conn = DBConnector.getDBConn();
            conn.setAutoCommit(false);
            cd.deleteCart(idcart);
            System.out.println("删除cart" + idcart);
            ps = conn.prepareStatement(insert_order);
            ps.setObject(1, order.getIdOrder());
            ps.setObject(2, order.getIduser());
            ps.setString(3, order.getPhone());
            ps.setObject(4, order.getAddress());
            ps.setString(5, order.getName());
            ps.executeUpdate();
            System.out.println("插入order" + order.getIdOrder());
            for (Product product : order.getItems().keySet()) {
                ps = conn.prepareStatement(insert_orderitem);
                ps.setObject(1, order.getIdOrder());
                ps.setObject(2, product.getId());
                ps.setObject(3, order.getItems().get(product));
                ps.setObject(4, product.getPrice());
                ps.executeUpdate();
                ps = conn.prepareStatement(changequantity);
                lastquantity = pd.getQuantity(product.getId());
                ps.setObject(1, lastquantity - order.getItems().get(product));
                ps.setObject(2, product.getId());
                ps.executeUpdate();
            }
            System.out.println("添加完毕order" + order.getIdOrder() + "所有商品");
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * @param idorder 订单号
     * @return 是否成功
     */
    public boolean deleteOrder(int idorder) {
        conn = DBConnector.getDBConn();
        PreparedStatement ps = null;
        String delete_orderitem = "DELETE FROM orderitem WHERE idorder = ?";
        String delete_order = "DELETE FROM order WHERE idorder = ?";
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(delete_orderitem);
            ps.setObject(1, idorder);
            ps.executeUpdate();
            ps = conn.prepareStatement(delete_order);
            ps.setObject(1, idorder);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * 根据用户id获取用户的全部订单号
     *
     * @param userID 用户id
     * @return 订单号List
     */
    private List<Integer> getOrderID(int userID) {
        conn = DBConnector.getDBConn();
        int orderID;
        String sql = "SELECT * FROM orders WHERE iduser=?";
        List<Integer> list = new ArrayList<Integer>();
        try {
            PreparedStatement ps = null;
            ResultSet rs;
            if (conn != null) {
                ps = conn.prepareStatement(sql);
            }
            if (ps != null) {
                ps.setInt(1, userID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    orderID = rs.getInt("idorder");
                    System.out.println("获取订单id : " + orderID);
                    list.add(orderID);
                }
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("获取订单ID失败");
        return list;
    }

    /**
     * 支付订单完成后修改数据库的操作
     *
     * @param orderId 订单id
     * @return 是否成功
     */
    public boolean orderPaid(int orderId) {
        try {
            String sql = "SELECT * orders WHRER idorder =" + orderId;
            if (conn == null || conn.isClosed())
                conn = DBConnector.getDBConn();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if (rs.next() && rs.getString("status").equals("未支付")) {
                sql = "UPDATE orders SET status = '已付款' WHERE idorder =" + orderId;
                conn.createStatement().executeUpdate(sql);
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取某一个订单
     *
     * @param orderId 订单id
     * @param userID 用户id
     * @return 订单
     */
    public Order getOrder(int orderId, int userID) {
        Order order = new Order();
        String sql = "SELECT * FROM orderInfo WHERE idorder=?";
        PreparedStatement ps = null;
        int amount;
        ResultSet rs;
        try {
            if (conn == null || conn.isClosed())
                conn = DBConnector.getDBConn();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            HashMap<Product, Integer> items = new HashMap<Product, Integer>();
            boolean statusAdded = false;
            while (rs.next()) {
                if (!statusAdded) {
                    order.setPhone(rs.getString("phone"));
                    order.setName(rs.getString("name"));
                    order.setStatus(rs.getString("ostatus"));
                    order.setAddress(rs.getString("address"));
                    statusAdded = true;
                }
                Product current = new Product();
                int id = rs.getInt("idproduct");
                current = ProductData.getProductByID(id);
                amount = rs.getInt("amount");
                items.put(current, amount);
            }
            order.setIdOrder(orderId);
            order.setIduser(userID);
            order.setItems(items);
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭数据库连接
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}

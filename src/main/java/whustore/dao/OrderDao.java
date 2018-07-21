package whustore.dao;

import org.springframework.stereotype.Repository;
import whustore.Hakari.HakariDB;
import whustore.data.ProductData;
import whustore.model.CustomerInfo;
import whustore.model.Order;
import whustore.model.Product;
import whustore.service.CustomerInfoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderDao implements OrderDaoIntf {


    /**
     * 获取用户的历史订单
     *
     * @param idUser 用户ID
     * @return 历史订单列表
     */
    public List<Order> getOrderList(int idUser) {
        CustomerInfoService infoService = new CustomerInfoService();
        List<Order> orderList = new ArrayList<>();
        List<Integer> orderIdList = getOrderIdLisByIdUser(idUser);
        String sql = "SELECT * FROM orderInfo_1 WHERE idorder = ?";

        try (Connection connection = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            for (Integer i : orderIdList) {
                ps.setInt(1, i);
                ResultSet rs = ps.executeQuery();
                Order order = new Order();
                HashMap<Product, Integer> items = new HashMap<>();
                boolean statusAdded = false;
                while (rs.next()) {
                    if (!statusAdded) {
                        CustomerInfo info = infoService.getCustomerInfoByIdcustomerInfo(rs.getInt("idcustomerInfo"));
                        order.setInfo(info);
                        order.setStatus(rs.getString("ostatus"));
                        order.setCreateDate(rs.getDate("createdsince"));
                        statusAdded = true;
                    }
                    Product current = ProductData.getProductByID(rs.getInt("idproduct"));
                    int amount = rs.getInt("amount");
                    items.put(current, amount);
                }
                order.setIdOrder(i);
                order.setIduser(idUser);
                order.setItems(items);
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }



    /**
     * 添加Order
     *
     * @param order  order对象
     *
     * @return 是否成功添加
     */
    public boolean addOrder(Order order) {

        ProductDao pd = new ProductDao();

        String insert_order = "INSERT INTO orders(idorder, iduser,idcustomerInfo,idteam) values(?,?,?,?)";
        String insert_orderitem = "INSERT INTO orderitem(idorder, idproduct, amount, price) values(?,?,?,?)";
        String changequantity = "UPDATE product SET quantity = ? WHERE idproduct = ?";
        try (Connection connection = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(insert_order)) {

            connection.setAutoCommit(false);

            ps.setObject(1, order.getIdOrder());
            ps.setObject(2, order.getIduser());
            ps.setInt(3, order.getInfo().getIdCustomerInfo());
            ps.setInt(4, order.getIdTeam());
            ps.executeUpdate();
            System.out.println("插入order" + order.getIdOrder());

            for (Product product : order.getItems().keySet()) {
                PreparedStatement ps2 = connection.prepareStatement(insert_orderitem);
                ps2.setObject(1, order.getIdOrder());
                ps2.setObject(2, product.getId());
                ps2.setObject(3, order.getItems().get(product));
                ps2.setObject(4, product.getPrice());
                ps2.executeUpdate();

                PreparedStatement ps3 = connection.prepareStatement(changequantity);
                int lastquantity = pd.getQuantity(product.getId());
                ps3.setObject(1, lastquantity - order.getItems().get(product));
                ps3.setObject(2, product.getId());
                ps3.executeUpdate();
            }
            System.out.println("添加完毕order" + order.getIdOrder() + "所有商品");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @param idorder 订单号
     * @return 是否成功删除
     */
    public boolean deleteOrder(int idorder) {

        String delete_orderitem = "DELETE FROM orderitem WHERE idorder = ?";
        String delete_order = "DELETE FROM orders WHERE idorder = ?";
        try (Connection connection = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(delete_orderitem)) {
            connection.setAutoCommit(false);
            ps.setObject(1, idorder);
            ps.executeUpdate();

            PreparedStatement ps2 = connection.prepareStatement(delete_order);
            ps2.setObject(1, idorder);
            ps2.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据用户或团队id获取全部订单号
     *
     * @param id id
     * @return 订单号List
     */
    private List<Integer> getOrderIdListById(int id, String sql) {

        List<Integer> list = new ArrayList<>();
        try (Connection connection = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs;
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("idorder");
                System.out.println("获取订单id : " + orderID);
                list.add(orderID);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("获取订单ID失败");
        return list;
    }

    private List<Integer> getOrderIdLisByIdUser(int idUser){
        String sql = "SELECT * FROM orders WHERE iduser = ? ORDER BY createdsince DESC";
        return getOrderIdListById(idUser, sql);
    }

    private List<Integer> getOrderIdListByIdteam(int idTeam){
        String sql = "SELECT * FROM orders WHERE iduser = ? ORDER BY createdsince DESC";
        return getOrderIdListById(idTeam, sql);
    }

    /**
     * 支付订单完成后修改数据库的操作
     *
     * @param orderId 订单id
     * @return 是否成功
     */
    public boolean orderPaid(int orderId) {
        String sql = "SELECT * FROM orders WHERE idorder=?";
        try (Connection connection = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getString("ostatus").equals("未付款")) {
                sql = "UPDATE orders SET ostatus = '已付款' WHERE idorder=?";
                PreparedStatement ps2 = connection.prepareStatement(sql);
                ps2.setInt(1, orderId);
                ps2.executeUpdate();
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
     * @param userID  用户id
     * @return 订单
     */
    public Order getOrderIduser(int orderId, int userID) {
        Order order = new Order();
        String sql = "SELECT * FROM orderInfo WHERE idorder=?";
        int amount;
        ResultSet rs;
        try (Connection connection = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            HashMap<Product, Integer> items = new HashMap<>();
            boolean statusAdded = false;
            while (rs.next()) {
                if (!statusAdded) {
                    order.setStatus(rs.getString("ostatus"));
                    order.setCreateDate(rs.getDate("createdsince"));
                    statusAdded = true;
                }
                int id = rs.getInt("idproduct");
                Product current = ProductData.getProductByID(id);
                amount = rs.getInt("amount");
                items.put(current, amount);
            }
            order.setIdOrder(orderId);
            order.setIduser(userID);
            order.setItems(items);
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void changeOstatus(int idorder, String status) {

        String sql = "UPDATE orders SET ostatus = ? WHERE idorder = ?";
        try (Connection connection = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setObject(1, idorder);
            ps.setObject(2, status);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

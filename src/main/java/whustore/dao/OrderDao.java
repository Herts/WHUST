package whustore.dao;

import whustore.model.*;

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
        conn = DBConnector.getDBConn();
        List<Order> orderlist = new ArrayList<Order>();
        List<Integer> orderidList = this.getOrderID(userID);
        String sql = "SELECT * FROM orderInfo WHERE idorder=?";
        int amount = 0;
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            ps = conn.prepareStatement(sql);
            for (Integer i : orderidList) {
                ps.setInt(1, i);
                rs = ps.executeQuery();
                Order order = new Order();
                HashMap<Product, Integer> items = new HashMap<Product, Integer>();
                while (rs.next()) {
                    Product current = new Product();
                    current.setId(rs.getInt("idproduct"));
                    current.setProductName(rs.getString("pname"));
                    current.setProIntro(rs.getString("description"));
                    current.setQuantity(rs.getInt("quantity"));
                    current.setPrice(rs.getDouble("price"));

                    //setType等待进一步实现
                    current.setType(null);
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
        }
        System.out.println("获取订单ID失败");
        return list;
    }
}

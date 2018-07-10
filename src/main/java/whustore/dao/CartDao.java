package whustore.dao;

import whustore.model.Cart;
import whustore.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartDao {
    Connection conn;

    /**
     * 获取用户的购物车
     *
     * @param userID
     * @return
     */
    public Cart getUserCart(int userID) {
        Cart cart = new Cart();
        conn = DBConnector.getDBConn();
        int idcart = getCartID(userID);
        String sql = "SELECT * FROM cartInfo WHERE idcart=?";

        PreparedStatement ps = null;
        ResultSet rs;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idcart);
            rs = ps.executeQuery();
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
                int amount = rs.getInt("amount");
                items.put(current, amount);
            }
            rs.last();
            cart.setCartID(rs.getInt("idcart"));
            cart.setItems(items);
            return cart;
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
        return cart;
    }

    /**
     * 获取购物车的ID
     *
     * @param userID
     * @return
     */
    private int getCartID(int userID) {
        conn = DBConnector.getDBConn();
        int cartID;
        String sql = "SELECT * FROM cart WHERE iduser=?";

        try {
            PreparedStatement ps = null;
            ResultSet rs;
            if (conn != null) {
                ps = conn.prepareStatement(sql);
            }
            if (ps != null) {
                ps.setInt(1, userID);
                rs = ps.executeQuery();
                rs.next();
                cartID = rs.getInt("idcart");
                System.out.println("获取购物车ID：" + cartID);
                return cartID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("获取购物车ID失败");
        return -1;
    }
}

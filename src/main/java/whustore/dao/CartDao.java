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

    public Cart getUserCart(int userID) {
        Cart cart = new Cart();
        cart.setUserID(userID);
        int cartID = getCartID(userID);
        if (cartID == -1)
            return null;
        cart.setCartID(cartID);
        HashMap<Product, Integer> items = new HashMap<Product, Integer>();
        items = getItems(cartID);
        cart.setItems(items);
        return cart;
    }

    /*
     * 根据用户id获取对应的购物车id
     * */
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

    /*
     * 根据cartID获取cart全部商品及其数量
     * */
    private HashMap<Product, Integer> getItems(int cartID) {
        if (conn == null)
            conn = DBConnector.getDBConn();
        PreparedStatement ps;
        ProductDao productDao = new ProductDao();
        HashMap<Product, Integer> items = new HashMap<Product, Integer>();

        try {
            ResultSet rs;
            String sql = "SELECT * FROM cartitem WHERE idcart=?";
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setInt(1,cartID);
                rs = ps.executeQuery();
                while (rs.next()) {
                 Product current = productDao.getProduct(rs.getInt("idcartitem"));
                 int amount = rs.getInt("amount");
                 items.put(current,amount);
                }
                return items;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
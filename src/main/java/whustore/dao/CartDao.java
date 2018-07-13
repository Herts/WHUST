package whustore.dao;

import whustore.data.ProductData;
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
        int cartID = getCartID(userID);
        String sql = "SELECT * FROM cartInfo WHERE idcart=?";

        PreparedStatement ps = null;
        ResultSet rs;
        try {
            conn = DBConnector.getDBConn();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cartID);
            rs = ps.executeQuery();
            HashMap<Product, Integer> items = new HashMap<Product, Integer>();
            while (rs.next()) {
                Product product = new Product();
                int productID = rs.getInt("idproduct");
                int amount = rs.getInt("amount");
                items.put(ProductData.getProductByID(productID), amount);
            }
            cart.setItems(items);
            cart.setCartID(cartID);
            cart.setUserID(userID);
            return cart;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭数据库连接

            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
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
        String sql = "SELECT * FROM cart WHERE iduser=?";

        try {

            int cartID;
            PreparedStatement ps = null;
            ResultSet rs;
            if (conn == null || conn.isClosed()) {
                conn = DBConnector.getDBConn();
            }
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                ps.setInt(1, userID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    cartID = rs.getInt("idcart");
                    System.out.println("获取购物车ID：" + cartID);
                    conn.close();
                    return cartID;
                } else {
                    conn.commit();
                    cartID = (int) ((System.currentTimeMillis() % 1000000) + (userID % 1000) * 1000000);
                    sql = "INSERT INTO cart (idcart, iduser) VALUES (?, ?)";
                    ps = conn.prepareStatement(sql);
                    ps.setInt(1, cartID);
                    ps.setInt(2, userID);
                    ps.executeUpdate();
                    return cartID;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public void addProductToCart(int userID, int productID, int num) {
        try {
            int cartID = getCartID(userID);
            //购物车已有该商品
            String sql = "SELECT amount FROM cartitem WHERE idproduct=? AND idcart=?";
            if (conn == null || conn.isClosed()) {
                conn = DBConnector.getDBConn();
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productID);
            ps.setInt(2, cartID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int originNum = rs.getInt(1);
                sql = "UPDATE cartitem SET amount=? WHERE idproduct=? AND  idcart=?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, num + originNum);
                ps.setInt(2, productID);
                ps.setInt(3, cartID);
                ps.executeUpdate();
            } else {
                //购物车还没有该商品
                sql = "INSERT INTO cartitem (idcart, idproduct, amount) VALUES (?, ?, ?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, cartID);
                ps.setInt(2, productID);
                ps.setInt(3, num);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean deleteCart (int idcart){
        boolean isdeleteitems = false;
        conn = DBConnector.getDBConn();
        String sql = "DELETE FROM cart WHERE idcart = ?";
        PreparedStatement ps = null;
        isdeleteitems = this.deleteCartitem(idcart);
        if(isdeleteitems){
            try {
                ps = conn.prepareStatement(sql);
                ps.setObject(1,idcart);
                ps.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    private boolean deleteCartitem ( int idcart){
        String sql = "DELETE FROM cartitem WHERE idcart = ?";
        conn = DBConnector.getDBConn();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1,idcart);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean addCart (int iduser,HashMap<Integer,Integer> pidANDamount){
        String sql = "INSERT INTO cart(idcart,iduser) values(?,?)";
        Connection conn = DBConnector.getDBConn();
        PreparedStatement ps = null;
        int idcart =(int) System.currentTimeMillis()/1000;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setObject(1,idcart);
            ps.setObject(2,iduser);
            for (Integer idproduct: pidANDamount.keySet()) {
                this.addProductToCart(iduser,idproduct,pidANDamount.get(idproduct));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                conn.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}

package whustore.dao;

import whustore.data.ProductData;
import whustore.model.Cart;
import whustore.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class CartDao implements CartDaoIntf{
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
                product = ProductData.getProductByID(productID);
                if (product != null)
                    items.put(product, amount);
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
     * @param userID 用户ID
     * @return 购物车ID
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

    /**
     * 把商品添加到购物车
     *
     * @param userID    用户id
     * @param productID 产品id
     * @param num       数量
     */
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

    public boolean deleteCart(int idcart) {
        boolean isdeleteitems = false;
        String sql = "DELETE FROM cart WHERE idcart = ?";
        PreparedStatement ps = null;
        isdeleteitems = this.deleteCartitem(idcart);
        if (isdeleteitems) {
            try {
                if (conn == null || conn.isClosed()) {
                    conn = DBConnector.getDBConn();
                }
                conn = DBConnector.getDBConn();
                ps = conn.prepareStatement(sql);
                ps.setObject(1, idcart);
                ps.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn == null || !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return false;
    }

    public boolean deleteCartitem(int idcart) {
        String sql = "DELETE FROM cartitem WHERE idcart = ?";

        PreparedStatement ps = null;

        try {
            if (conn == null || conn.isClosed()) {
                conn = DBConnector.getDBConn();
            }
            ps = conn.prepareStatement(sql);
            ps.setObject(1, idcart);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn == null || !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 如果购物车里有该商品该商品数量减一
     *
     * @param productID 产品艾迪
     * @param userID    用户艾迪
     * @return 成功与否
     */
    public boolean subProductInCart(int productID, int userID) {
        int cartID = getCartID(userID);
        String sql = "SELECT amount FROM cartitem WHERE idproduct=? AND idcart=?";
        try {
            if (conn == null || conn.isClosed()) {
                conn = DBConnector.getDBConn();
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productID);
            ps.setInt(2, cartID);
            ResultSet rs = ps.executeQuery();
            if (rs.getFetchSize() != 0) {
                int oldAmount = rs.getInt(1);
                if (oldAmount == 1) {
                    return removeProduct(productID, cartID);
                }
                sql = "UPDATE cartitem SET amount=? WHERE idproduct=? AND idcart=?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, oldAmount - 1);
                ps.setInt(2, productID);
                ps.setInt(3, cartID);
                ps.executeUpdate();
                return true;
            } else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn == null || !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从数据库中移除产品
     *
     * @param productID 产品id
     * @param cartID    购物车id
     * @return 成功与否
     */
    public boolean removeProduct(int productID, int cartID) {
        String sql = "DELETE FROM cartitem WHERE idproduct=? AND idcart=?";
        try {
            if (conn == null || conn.isClosed()) {
                conn = DBConnector.getDBConn();
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productID);
            ps.setInt(2, cartID);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}

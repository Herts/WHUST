package whustore.dao;

import com.mysql.cj.jdbc.PreparedStatement;
import whustore.model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDao {
    private Connection conn;

    public Product getProduct(int productID)
    {
        conn = DBConnector.getDBConn();
        java.sql.PreparedStatement ps;
        Product product;

        try {
            ResultSet rs;
            String sql = "SELECT * FROM product WHERE idproduct=?";
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setInt(1,productID);
                rs = ps.executeQuery();
                rs.next();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

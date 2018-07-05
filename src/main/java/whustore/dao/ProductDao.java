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
        Product product = new Product();

        try {
            ResultSet rs;
            String sql = "SELECT * FROM product WHERE idproduct=?";
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setInt(1,productID);
                rs = ps.executeQuery();
                rs.next();
                product.setId(rs.getInt("idproduct"));
                product.setProductName(rs.getString("pname"));
                product.setProIntro(rs.getString("description"));
                product.setQuantity(rs.getInt("quantity"));
                //setType等待进一步实现
                product.setType(null);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}

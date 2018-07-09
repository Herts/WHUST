package whustore.dao;

import com.mysql.cj.jdbc.PreparedStatement;
import whustore.model.*;

import java.sql.*;

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

    public boolean addProduct (Product p){
        conn = DBConnector.getDBConn();
        java.sql.PreparedStatement ps;
        int state = 0;
        boolean restate=false;
        String sql = "INSERT INTO product(pname,description,quantity,idteam,price) VALUES (?,?,?,?,?)";

        try {
            if(conn!=null && p!=null) {
                ps = conn.prepareStatement(sql);
                ps.setObject(1,p.getProductName());
                ps.setObject(2,p.getProIntro());
                ps.setObject(3,p.getQuantity());
                ps.setObject(4,p.getTeamID());
                ps.setObject(5,p.getPrice());
                state = ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        if(state == 0)
            restate = false;
        if(state ==1)
            restate = false;

        return restate;
    }
    public boolean addPicture(Picture p){
        conn = DBConnector.getDBConn();
        java.sql.PreparedStatement ps;
        int state = 0;
        boolean restate = false;
        String sql = "insert into picture(ppath,ptype) values (?,?)";

        try {
            if(conn!=null && p!=null) {
                ps = conn.prepareStatement(sql);
                ps.setObject(1,p.getPpath());
                ps.setObject(2,p.getPtype());
                state = ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        if(state == 0)
            restate = false;
        if(state ==1)
            restate = true;

        return restate;
    }

    public boolean addProductPicture(Product product,Picture picture){
        conn = DBConnector.getDBConn();
        java.sql.PreparedStatement ps;
        int state = 0;
        boolean restate = false;
        String sql = "insert into productpic(idproduct,idpicture) values (?,?)";

        try {
            if(product!=null && picture!=null){
                ps = conn.prepareStatement(sql);
                ps.setObject(1,product.getId());
                ps.setObject(2,picture.getIdpicture());
                state = ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(state ==0)
            restate=false;
        if(state==1)
            restate=true;
        return restate;
    }

}

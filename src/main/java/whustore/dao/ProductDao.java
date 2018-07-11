package whustore.dao;

import com.mysql.cj.jdbc.PreparedStatement;
import whustore.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        int idproduct =(int) (System.currentTimeMillis() / 1000);
        List<Integer> idcategory = new ArrayList<Integer>();
        List<Integer> idpicture = new ArrayList<Integer>();
        String insert_product = "INSERT INTO product(idproduct,pname,description,quantity,idteam,price) VALUES (?,?,?,?,?,?)";
        String select_idcategory = "SELECT idcategory FROM category WHERE cname = ?";
        String insert_picture = "INSERT INTO picture(ppath) values(?)";
        String select_idpicture = "SELECT * FROM picture WHERE ppath = ?";
        String insert_procat = "INSERT INTO procat(idproduct,idcategory) values(?,?)";
        String insert_productpic = "INSERT INTO productpic(idproduct,idpicture)values(?,?)";
        ResultSet rs = null;
        try {
            conn.setAutoCommit(false);
            if(conn!=null && p!=null) {
                ps = conn.prepareStatement(insert_product);
                ps.setInt(1,idproduct);
                ps.setObject(2,p.getProductName());
                ps.setObject(3,p.getProIntro());
                ps.setObject(4,p.getQuantity());
                ps.setObject(5,p.getTeamID());
                ps.setObject(6,p.getPrice());
                ps.executeUpdate();

                if(p.getPicPath()!=null && p.getPicPath().size() >0){
                    for (String str: p.getPicPath()) {
                        ps = conn.prepareStatement(insert_picture);
                        ps.setString(1,str);
                        int a = ps.executeUpdate();
                        ps = conn.prepareStatement(select_idpicture);
                        ps.setString(1,str);
                        rs = ps.executeQuery();
                        rs.next();
                        int id = rs.getInt("idpicture");
                        idpicture.add(id);
                    }
                }


                if(idpicture.size()>0){

                    for (Integer in: idpicture) {
                        ps = conn.prepareStatement(insert_productpic);
                        ps.setInt(1,idproduct);
                        ps.setInt(2,in);
                        ps.executeUpdate();
                    }
                }

                if(p.getType()!=null && p.getType().size()>1){
                    for ( String str: p.getType()  ) {
                        ps = conn.prepareStatement(select_idcategory);
                        ps.setString(1,str);
                        rs = ps.executeQuery();
                        rs.next();
                        idcategory.add(rs.getInt("idcategory"));
                    }
                }

                if(idcategory.size() >0){
                    for(Integer in : idcategory){
                        ps = conn.prepareStatement(insert_procat);
                        ps.setInt(1,idproduct);
                        ps.setInt(2,in);
                        ps.executeUpdate();
                    }
                }

            }
            state = 1;
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        if(state == 0)
            restate = false;
        if(state ==1)
            restate = true;
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

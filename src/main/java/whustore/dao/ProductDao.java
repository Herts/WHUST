package whustore.dao;

import whustore.model.Picture;
import whustore.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDao {
    private Connection conn;

    /**
     * 获取单个商品的Model
     *
     * @param productID
     * @return
     */
    public Product getProduct(int productID) {
        conn = DBConnector.getDBConn();
        PreparedStatement ps = null;
        Product product = new Product();
        String select_paths = "SELECT * FROM productpic NATURAL JOIN picture WHERE idproduct = ?";
        String select_types = "SELECT * FROM product NATURAL JOIN product WHERE idproduct = ?";
        try {
            ResultSet rs;
            String sql = "SELECT * FROM product WHERE idproduct=?";
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, productID);
                rs = ps.executeQuery();
                while(rs.next()){
                    product.setId(rs.getInt("idproduct"));
                    product.setProductName(rs.getString("pname"));
                    product.setProIntro(rs.getString("description"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setTeamID(rs.getInt("idteam"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStatus(rs.getInt("status"));

                }
                ps = conn.prepareStatement(select_paths);
                ps.setObject(1,productID);
                rs = ps.executeQuery();
                while (rs.next()){
                    if(rs.getString("ppath")!=null){
                        product.picPathAppend(rs.getString("ppath"));
                    }
                }
                ps = conn.prepareStatement(select_types);
                ps.setObject(1,productID);
                rs = ps.executeQuery();
                while(rs.next()){
                    if(rs.getString("cname")!=null){
                        product.typeAppend(rs.getString("cname"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return product;
    }

    public List<Product> getAllProductOrderByPrice() {
        String sql = "SELECT * FROM productinfo ORDER BY price";
        return getAllProduct(sql);
    }

    public List<Product> getAllProduct() {
        String sql = "SELECT * FROM productinfo";
        return getAllProduct(sql);
    }


    private List<Product> getAllProduct(String sql) {
        conn = DBConnector.getDBConn();
        List<Product> list = new ArrayList<Product>();
        if (conn == null)
            return null;
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            Product lastProduct = new Product();
            while (rs.next()) {
                int idproduct = rs.getInt("idproduct");
                //是一个新的产品时新建产品
                if (lastProduct.getId() != idproduct) {
                    Product current = new Product();
                    current.setId(idproduct);
                    current.setProductName(rs.getString("pname"));
                    current.setProIntro(rs.getString("description"));
                    current.setQuantity(rs.getInt("quantity"));
                    current.setTeamID(rs.getInt("idteam"));
                    current.setPrice(rs.getDouble("price"));
                    current.picPathAppend(rs.getString("ppath"));
                    current.typeAppend(rs.getString("category"));
                    list.add(current);
                    lastProduct = current;
                } else {
                    //是上一个产品的新ppath或者category时只尝试添加这两个值
                    lastProduct.picPathAppend(rs.getString("ppath"));
                    lastProduct.typeAppend(rs.getString("category"));
                }
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean addProduct(Product p) {
        conn = DBConnector.getDBConn();
        PreparedStatement ps;
        int state = 0;
        boolean restate=false;
        int idproduct =(int) (System.currentTimeMillis() / 1000);
        List<Integer> idcategory = new ArrayList<Integer>();
        List<Integer> idpicture = new ArrayList<Integer>();
        String insert_product = "INSERT INTO product(idproduct,pname,description,quantity,idteam,price) VALUES (?,?,?,?,?,?)";
        String insert_picture = "INSERT INTO picture(ppath) values(?)";
        String select_idpicture = "SELECT * FROM picture WHERE ppath = ?";
        String insert_procat = "INSERT INTO procat(idproduct,cname) values(?,?)";
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

                if(p.getTypes()!=null && p.getTypes().size()>0){
                    for (String t: p.getTypes()
                            ) {
                        ps = conn.prepareStatement(insert_procat);
                        ps.setObject(1,idproduct);
                        ps.setObject(2,t);
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
        finally {
            //关闭数据库连接
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(state == 0)
            restate = false;
        if(state ==1)
            restate = true;
        return restate;
    }


    public boolean addPicture(Picture p) {
        conn = DBConnector.getDBConn();
        PreparedStatement ps;
        int state = 0;
        boolean restate = false;
        String sql = "insert into picture(ppath,ptype) values (?,?)";

        try {
            if (conn != null && p != null) {
                ps = conn.prepareStatement(sql);
                ps.setObject(1, p.getPpath());
                ps.setObject(2, p.getPtype());
                state = ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        finally {
            //关闭数据库连接
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (state == 0)
            restate = false;
        if (state == 1)
            restate = true;

        return restate;
    }
    public boolean addProductPicture(Product product, Picture picture) {
        conn = DBConnector.getDBConn();
        PreparedStatement ps;
        int state = 0;
        boolean restate = false;
        String sql = "insert into productpic(idproduct,idpicture) values (?,?)";

        try {
            if (product != null && picture != null) {
                ps = conn.prepareStatement(sql);
                ps.setObject(1, product.getId());
                ps.setObject(2, picture.getIdpicture());
                state = ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            //关闭数据库连接
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (state == 0)
            restate = false;
        if (state == 1)
            restate = true;
        return restate;
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<Product>();
        conn = DBConnector.getDBConn();
        String sql = "SELECT * FROM product";
        try {
            PreparedStatement ps;
            ResultSet rs;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = getProductFromResultSet(rs);
                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            //关闭数据库连接
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<Product> getProductsByType(String type) {
        List<Product> list = new ArrayList<Product>();
        conn = DBConnector.getDBConn();
        String sql = "SELECT * FROM category WHERE cname = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, type);
            rs = ps.executeQuery();
            rs.next();
            int idcategory = rs.getInt("idcategory");
            sql = "SELECT * FROM procat NATURAL JOIN product WHERE idcategory = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idcategory);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = getProductFromResultSet(rs);
                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            //关闭数据库连接
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<Product> getProductsByQuery(String query) {
        List<Product> list = new ArrayList<Product>();
        conn = DBConnector.getDBConn();
        String sql = "SELECT * FROM product WHERE pname LIKE " + "\"" + "%" + query + "%" + "\"";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = getProductFromResultSet(rs);
                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            //关闭数据库连接
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;

    }

    public List<Product> getRandomProducts(int k) {
        List<Product> list = new ArrayList<Product>();
        conn = DBConnector.getDBConn();
        String sql = "SELECT COUNT(*) FROM product";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int n = 0;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            n = rs.getInt(1);
            if (k <= n) {

            } else {
                k = n;
            }
            List<Integer> random = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) random.add(i);
            Collections.shuffle(random);
            random = random.subList(0, k);
            sql = "SELECT * FROM product";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            int j = 0;
            while (rs.next()) {
                if (random.contains(j)) {
                    Product p = getProductFromResultSet(rs);
                    list.add(p);
                }
                j++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            //关闭数据库连接
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public boolean changeQuantity(int idproduct, int change) {
        conn = DBConnector.getDBConn();
        PreparedStatement ps = null;
        String sql = "UPDATE product SET quantity = ? WHERE idproduct = ?";
        int lastquantity = 0;
        int state = 0;
        boolean restate = false;
        lastquantity = this.getQuantity(idproduct);
        if ((lastquantity + change) < 0) {
            return false;
        }
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, lastquantity + change);
            ps.setObject(2, idproduct);
            state = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            //关闭数据库连接
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (state == 0) {
            restate = false;
        } else {
            restate = true;
        }
        return restate;
    }

    public int getQuantity(int idproduct) {
        PreparedStatement ps = null;
        String sql = "select * from product where idproduct = ?";
        ResultSet rs = null;
        int quantity = 0;
        try {
            if (conn == null || conn.isClosed()) {
                conn = DBConnector.getDBConn();
            }
            ps = conn.prepareStatement(sql);
            ps.setObject(1, idproduct);
            rs = ps.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("quantity");
                return quantity;
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
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
    public List<Product> getProductsByidteam (int idteam){
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE idteam = ?";
        String select_path = "SELECT * FROM productpic natural join picture WHERE idproduct = ?";
        String select_types = "SELECT * FROM procat WHERE idproduct = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rs_idproduct = null;
        int idproduct = 0;
        conn = DBConnector.getDBConn();
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1,idteam);
            rs_idproduct = ps.executeQuery();
            while (rs_idproduct.next()){
                Product p = new Product();
                idproduct = rs_idproduct.getInt("idproduct");
                p.setId(idproduct);
                p.setPrice(rs_idproduct.getDouble("price"));
                p.setTeamID(idteam);
                p.setQuantity(rs_idproduct.getInt("quantity"));
                p.setProIntro(rs_idproduct.getString("description"));
                p.setProductName(rs_idproduct.getString("pname"));
                ps = conn.prepareStatement(select_types);
                ps.setObject(1,idproduct );
                rs = ps.executeQuery();
                while(rs.next()){
                    p.typeAppend(rs.getString("cname"));
                }
                ps = conn.prepareStatement(select_path);
                ps.setObject(1,idproduct);
                rs= ps.executeQuery();
                while(rs.next()){
                    p.picPathAppend(rs.getString("ppath"));
                }
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public boolean changeStatus (int idproduct, int status){
        String select_sql = "SELECT * FROM product WHERE idproduct= ?";
        String update_sql = "UPDATE product SET status = ? WHERE idproduct = ?";
        conn = DBConnector.getDBConn();
        PreparedStatement ps = null;
        int oldStatus = 0;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(select_sql);
            ps.setObject(1,idproduct);
            rs = ps.executeQuery();
            if(rs.next()){
                oldStatus = rs.getInt("status");
                if(oldStatus ==status){
                    return true;
                }
            }else {
                return true;
            }
            ps = conn.prepareStatement(update_sql);
            ps.setObject(1,status);
            ps.setObject(2,idproduct);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    public List<Product> getProductsByStatus (int status){
        conn = DBConnector.getDBConn();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM product WHERE status = ?";
        List<Product> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1,status);
            rs = ps.executeQuery();
            while(rs.next()){
                int idproduct = rs.getInt("idproduct");
                list.add(this.getProduct(idproduct));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    private Product getProductFromResultSet(ResultSet rs) {
        Product p = new Product();
        try {
            p.setId(rs.getInt("idproduct"));
            p.setProductName(rs.getString("pname"));
            p.setProIntro(rs.getString("description"));
            p.setQuantity(rs.getInt("quantity"));
            p.setTeamID(rs.getInt("idteam"));
            p.setPrice(rs.getDouble("price"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;

    }
}

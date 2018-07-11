package whustore.dao;

import whustore.model.*;

import java.sql.*;
import java.util.*;

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
        java.sql.PreparedStatement ps;
        Product product = new Product();

        try {
            ResultSet rs;
            String sql = "SELECT * FROM product WHERE idproduct=?";
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, productID);
                rs = ps.executeQuery();
                rs.next();
                product.setId(rs.getInt("idproduct"));
                product.setProductName(rs.getString("pname"));
                product.setProIntro(rs.getString("description"));
                product.setQuantity(rs.getInt("quantity"));
                //setType等待进一步实现
                product.setTypes(null);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> getAllProductOrderByPrice() {
        String sql = "SELECT * FROM productInfo ORDER BY price";
        return getAllProduct(sql);
    }

    public List<Product> getAllProduct() {
        String sql = "SELECT * FROM productInfo";
        return getAllProduct(sql);
    }


    public List<Product> getAllProduct(String sql) {
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
        java.sql.PreparedStatement ps;
        int state = 0;
        boolean restate = false;
        String sql = "INSERT INTO product(pname,description,quantity,idteam,price) VALUES (?,?,?,?,?)";

        try {
            if (conn != null && p != null) {
                ps = conn.prepareStatement(sql);
                ps.setObject(1, p.getProductName());
                ps.setObject(2, p.getProIntro());
                ps.setObject(3, p.getQuantity());
                ps.setObject(4, p.getTeamID());
                ps.setObject(5, p.getPrice());
                state = ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        if (state == 0)
            restate = false;
        if (state == 1)
            restate = false;

        return restate;
    }

    public boolean addPicture(Picture p) {
        conn = DBConnector.getDBConn();
        java.sql.PreparedStatement ps;
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
        if (state == 0)
            restate = false;
        if (state == 1)
            restate = true;

        return restate;
    }

    public boolean addProductPicture(Product product, Picture picture) {
        conn = DBConnector.getDBConn();
        java.sql.PreparedStatement ps;
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

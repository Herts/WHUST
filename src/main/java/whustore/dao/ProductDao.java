package whustore.dao;

import whustore.Hakari.HakariDB;
import whustore.model.Picture;
import whustore.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {


    /**
     * 获取单个商品的Model
     *
     * @param productID 商品id
     * @return 商品Model
     */
    public Product getProduct(int productID) {
        Product product = new Product();
        String sql = "SELECT * FROM product WHERE idproduct=?";
        String select_paths = "SELECT * FROM productpic NATURAL JOIN picture WHERE idproduct = ?";
        String select_types = "SELECT * FROM product NATURAL JOIN procat WHERE idproduct = ?";
        try (Connection connection = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs;

            ps.setInt(1, productID);
            rs = ps.executeQuery();
            if (rs.next()) {
                product = this.getProductFromResultSet(rs);
            }

            PreparedStatement ps2 = connection.prepareStatement(select_paths);
            ps2.setObject(1, productID);
            rs = ps2.executeQuery();
            while (rs.next()) {
                if (rs.getString("ppath") != null) {
                    product.picPathAppend(rs.getString("ppath"));
                }
            }

            PreparedStatement ps3 = connection.prepareStatement(select_types);
            ps3.setObject(1, productID);
            rs = ps3.executeQuery();
            while (rs.next()) {
                if (rs.getString("cname") != null) {
                    product.typeAppend(rs.getString("cname"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        List<Product> list = new ArrayList<>();
        try (Connection conn = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery(sql);
            Product lastProduct = new Product();
            while (rs.next()) {
                int idproduct = rs.getInt("idproduct");
                //是一个新的产品时新建产品
                if (lastProduct.getId() != idproduct) {
                    Product current = this.getProductFromResultSet(rs);
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
        }
    }

    public boolean addProduct(Product p) {
        int state = 0;
        int idproduct = (int) (System.currentTimeMillis() / 1000);
        List<Integer> idpicture = new ArrayList<Integer>();
        String insert_product = "INSERT INTO product(idproduct,pname,description,quantity,idteam,price) VALUES (?,?,?,?,?,?)";
        String insert_picture = "INSERT INTO picture(ppath) values(?)";
        String select_idpicture = "SELECT * FROM picture WHERE ppath = ?";
        String insert_procat = "INSERT INTO procat(idproduct,cname) values(?,?)";
        String insert_productpic = "INSERT INTO productpic(idproduct,idpicture)values(?,?)";
        try (Connection conn = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(insert_product)) {
            conn.setAutoCommit(false);
            if (p != null) {
//                ps = conn.prepareStatement(insert_product);
                ps.setInt(1, idproduct);
                ps.setObject(2, p.getProductName());
                ps.setObject(3, p.getProIntro());
                ps.setObject(4, p.getQuantity());
                ps.setObject(5, p.getTeamID());
                ps.setObject(6, p.getPrice());
                ps.executeUpdate();

                if (p.getPicPath() != null && p.getPicPath().size() > 0) {
                    ResultSet rs;
                    for (String str : p.getPicPath()) {
                        PreparedStatement ps2 = conn.prepareStatement(insert_picture);
                        ps2.setString(1, str);
                        int a = ps2.executeUpdate();

                        PreparedStatement ps3 = conn.prepareStatement(select_idpicture);
                        ps3.setString(1, str);
                        rs = ps3.executeQuery();

                        rs.next();
                        int id = rs.getInt("idpicture");
                        idpicture.add(id);
                    }
                }


                if (idpicture.size() > 0) {

                    for (Integer in : idpicture) {
                        PreparedStatement ps4 = conn.prepareStatement(insert_productpic);
                        ps4.setInt(1, idproduct);
                        ps4.setInt(2, in);
                        ps4.executeUpdate();
                    }
                }

                if (p.getTypes() != null && p.getTypes().size() > 0) {
                    for (String t : p.getTypes()
                            ) {
                        PreparedStatement ps5 = conn.prepareStatement(insert_procat);
                        ps5.setObject(1, idproduct);
                        ps5.setObject(2, t);
                        ps5.executeUpdate();
                    }
                }
            }
            state = 1;
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state == 1;
    }


    public boolean addPicture(Picture p) {
        int state = 0;
        String sql = "insert into picture(ppath,ptype) values (?,?)";
        try (Connection conn = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if (p != null) {
                ps.setObject(1, p.getPpath());
                ps.setObject(2, p.getPtype());
                state = ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return state == 1;
    }

    public boolean addProductPicture(Product product, Picture picture) {
        int state = 0;
        String sql = "insert into productpic(idproduct,idpicture) values (?,?)";

        try (Connection conn = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if (product != null && picture != null) {
                ps.setObject(1, product.getId());
                ps.setObject(2, picture.getIdpicture());
                state = ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (state == 1);
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Connection conn = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
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
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM category WHERE cname = ?";
        try (Connection conn = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int idcategory = rs.getInt("idcategory");
            sql = "SELECT * FROM procat NATURAL JOIN product WHERE idcategory = ?";

            PreparedStatement ps2 = conn.prepareStatement(sql);
            ps2.setInt(1, idcategory);
            rs = ps2.executeQuery();
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
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE pname LIKE '%" + query +"%'";
        try (Connection conn = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = getProductFromResultSet(rs);
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }


    public boolean changeQuantityByIdProduct(int idproduct, int change) {
        String sql = "UPDATE product SET quantity = ? WHERE idproduct = ?";
        int state = 0;
        int lastQuantity = this.getQuantity(idproduct);
        if ((lastQuantity + change) < 0) {
            return false;
        }
        try (Connection conn = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, lastQuantity + change);
            ps.setObject(2, idproduct);
            state = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state == 1;
    }

    public int getQuantity(int idproduct) {
        String sql = "select * from product where idproduct = ?";
        try (Connection conn = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, idproduct);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int quantity = rs.getInt("quantity");
                return quantity;
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Product> getProductsByidteam(int idteam) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE idteam = ?";
        String select_path = "SELECT * FROM productpic natural join picture WHERE idproduct = ?";
        String select_types = "SELECT * FROM procat WHERE idproduct = ?";
        int idproduct = 0;
        try (Connection conn = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, idteam);
            ResultSet rs_idproduct = ps.executeQuery();
            ResultSet rs = null;
            while (rs_idproduct.next()) {
                Product p = new Product();
                idproduct = rs_idproduct.getInt("idproduct");
                p.setId(idproduct);
                p.setPrice(rs_idproduct.getDouble("price"));
                p.setTeamID(idteam);
                p.setQuantity(rs_idproduct.getInt("quantity"));
                p.setProIntro(rs_idproduct.getString("description"));
                p.setProductName(rs_idproduct.getString("pname"));
                p.setStatus(rs_idproduct.getInt("status"));

                PreparedStatement ps2 = conn.prepareStatement(select_types);
                ps2.setObject(1, idproduct);
                rs = ps2.executeQuery();
                while (rs.next()) {
                    p.typeAppend(rs.getString("cname"));
                }

                PreparedStatement ps3 = conn.prepareStatement(select_path);
                ps3.setObject(1, idproduct);
                rs = ps3.executeQuery();
                while (rs.next()) {
                    p.picPathAppend(rs.getString("ppath"));
                }
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean changeStatus(int idproduct, int status) {
        String select_sql = "SELECT * FROM product WHERE idproduct= ?";
        String update_sql = "UPDATE product SET status = ? WHERE idproduct = ?";
        int oldStatus = 0;
        ResultSet rs = null;
        try (Connection conn = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(select_sql)) {
            ps.setObject(1, idproduct);
            rs = ps.executeQuery();
            if (rs.next()) {
                oldStatus = rs.getInt("status");
                if (oldStatus == status) {
                    return true;
                }
            } else {
                System.out.println("Product not found");
                return false;
            }
            PreparedStatement ps2 = conn.prepareStatement(update_sql);
            ps2.setObject(1, status);
            ps2.setObject(2, idproduct);
            ps2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Product> getProductsByStatus(int status) {
        String sql = "SELECT * FROM product WHERE status = ?";
        List<Product> list = new ArrayList<>();
        try (Connection conn = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idproduct = rs.getInt("idproduct");
                list.add(this.getProduct(idproduct));
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
            p.setStatus(rs.getInt("status"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;

    }
}

package whustore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import whustore.model.Product;
import whustore.model.Recommend;
@Repository
public class RecommendDao {
    Connection conn;

    public RecommendDao() {
    }

    public Recommend getRecommendation() {
        this.conn = DBConnector.getDBConn();
        Recommend rec = new Recommend();
        String sql = "SELECT * FROM recommend";
        PreparedStatement ps;
        ArrayList<Product> recommendList = new ArrayList<Product>();
        Product lastp = new Product();
        lastp.setId(0);
        try {
            ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                //是否已经加入
                int currentID = rs.getInt("idproduct");
                //第一次加入或者还未加入
                if (currentID!=lastp.getId()||lastp.getId()==0) {
                    //list里尚无该id商品信息
                    Product current = new Product();
                    current.setId(rs.getInt("idproduct"));
                    current.setProductName(rs.getString("pname"));
                    current.setProIntro(rs.getString("description"));
                    current.setPrice(rs.getDouble("price"));
                    current.picPathAppend(rs.getString("ppath"));
                    recommendList.add(current);
                    lastp = current;
                } else {
                    lastp.picPathAppend(rs.getString("ppath"));
                }
            }

            rs.last();
            rec.setRecommendList(recommendList);

            return rec;
        } catch (SQLException var16) {
            var16.printStackTrace();
        } finally {
            if (this.conn != null) {
                try {
                    this.conn.close();
                } catch (SQLException var15) {
                    var15.printStackTrace();
                }
            }

        }

        return rec;
    }

    private Product getFromListByID(ArrayList<Product> list,int id)
    {
        for (Product product:
             list) {
            if (product.getId()==id)
                return product;
        }
        return null;
    }
}

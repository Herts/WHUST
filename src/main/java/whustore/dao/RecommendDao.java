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

    /**
     * 获取商品推荐
     * TODO：实现推荐算法
     * @return
     */
    public Recommend getRecommendation() {
        this.conn = DBConnector.getDBConn();
        Recommend rec = new Recommend();
        String sql = "SELECT * FROM recommend";
        PreparedStatement ps;
        ArrayList<Product> recommendList = new ArrayList<Product>();

        try {
            ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                //是否已经加入
                Product productInList = getFromListByID(recommendList,rs.getInt("idproduct"));
                if (productInList==null) {
                    //list里尚无该id商品信息
                    Product current = new Product();
                    current.setId(rs.getInt("idproduct"));
                    current.setProductName(rs.getString("pname"));
                    current.setProIntro(rs.getString("description"));
                    current.setPrice(rs.getDouble("price"));
                    current.picPathAppend(rs.getString("ppath"));
                    recommendList.add(current);
                } else {
                    productInList.picPathAppend(rs.getString("ppath"));
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

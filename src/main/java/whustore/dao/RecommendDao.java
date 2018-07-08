package whustore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import whustore.model.Product;
import whustore.model.Recommend;

public class RecommendDao {
    Connection conn;

    public RecommendDao() {
    }

    public Recommend getRecommendation() {
        this.conn = DBConnector.getDBConn();
        Recommend rec = new Recommend();
        String sql = "SELECT * FROM recommend";
        PreparedStatement ps;
        ArrayList recommendList = new ArrayList();

        try {
            ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Product current = new Product();
                current.setId(rs.getInt("idproduct"));
                current.setProductName(rs.getString("pname"));
                current.setProIntro(rs.getString("description"));
                current.setPrice(rs.getDouble("price"));
                current.picPathAppend(rs.getString("ppath"));
                recommendList.add(current);
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
}

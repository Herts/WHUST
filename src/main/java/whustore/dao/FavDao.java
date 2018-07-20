package whustore.dao;


import whustore.Hakari.HakariDB;
import whustore.model.Product;
import whustore.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class FavDao {

    /**
     * 改变收藏状态
     *
     * @param iduser    用户艾迪
     * @param idproduct 产品艾迪
     * @return 状态
     */
    public int changeFavState(int iduser, int idproduct) {
        String sql = "SELECT state FROM fav WHERE iduser = ? AND idproduct = ?";

        try (Connection conn = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, iduser);
            ps.setInt(2, idproduct);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                boolean state = rs.getBoolean("state");
                if (state) {
                    sql = "UPDATE fav SET state = false WHERE iduser = ? AND idproduct = ? ";
                    PreparedStatement ps2 = conn.prepareStatement(sql);
                    ps2.setInt(1, iduser);
                    ps2.setInt(2, idproduct);
                    ps2.executeUpdate();
                    return 1;
                }
                sql = "UPDATE fav SET state = true WHERE iduser = ? AND idproduct = ? ";
                PreparedStatement ps3 = conn.prepareStatement(sql);
                ps3.setInt(1, iduser);
                ps3.setInt(2, idproduct);
                ps3.executeUpdate();
                return 0;
            }
            sql =  "INSERT INTO fav(iduser, idproduct, state) VALUES(?,?,?)";
            PreparedStatement ps4 = conn.prepareStatement(sql);
            ps4.setInt(1, iduser);
            ps4.setInt(2, idproduct);
            ps4.setInt(3,0);
            ps4.executeUpdate();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 2;
        }
    }

    /**
     * 获取某一用户收藏商品的商品艾迪列表
     *
     * @param iduser 用户艾迪
     * @return 用户所收藏商品的商品艾迪列表
     */
    public ArrayList<Integer> getIdproductByIduser(int iduser) {
        String sql = "SELECT idproduct FROM fav WHERE iduser = ?";
        ArrayList<Integer> list = new ArrayList<>();
        try (Connection conn = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, iduser);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("idproduct"));
            }
            return list;

        } catch (SQLException e) {

            e.printStackTrace();
            return null;
        }

    }
}

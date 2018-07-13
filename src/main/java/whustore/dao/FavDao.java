package whustore.dao;


import whustore.model.Product;
import whustore.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class FavDao {
    private Connection connection;
    private PreparedStatement ps;

    public int changeFavState(int iduser, int idproduct){
        connection = DBConnector.getDBConn();
        String sql = "SELECT state FROM fav WHERE iduser = ? AND idproduct = ?";

        try{

            ps = this.connection.prepareStatement(sql);
            ps.setInt(1, iduser);
            ps.setInt(2, idproduct);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                boolean state = rs.getBoolean("state");
                if (state) {
                    sql = "UPDATE fav SET state = false WHERE iduser = ? AND idproduct = ? ";
                    ps = connection.prepareStatement(sql);
                    ps.setInt(1, iduser);
                    ps.setInt(2, idproduct);
                    return 1;
                }
                sql = "UPDATE fav SET state = true WHERE iduser = ? AND idproduct = ? ";
                ps = connection.prepareStatement(sql);
                ps.setInt(1, iduser);
                ps.setInt(2, idproduct);
                return 0;
            }
            sql =  "INSERT INTO fav(iduser, idproduct, state) VALUES(?,?, )";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, iduser);
            ps.setInt(2, idproduct);
            return 0;
        }
        catch(SQLException e){
            e.printStackTrace();
            return 2;
        }
    }

    public ArrayList<Integer> getIdproductByIduser(int iduser){
        connection = DBConnector.getDBConn();
        String sql = "SELECT idproduct FROM fav WHERE iduser = ? AND state = true";
        ArrayList<Integer> list = new ArrayList<Integer>();
        try{
            connection = DBConnector.getDBConn();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, iduser);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(rs.getInt("idproduct"));
            }
            rs.next();
            return list;

        }
        catch (SQLException e){

            e.printStackTrace();
            return null;
        }

    }
}

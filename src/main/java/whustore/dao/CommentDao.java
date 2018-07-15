package whustore.dao;

import whustore.model.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao implements CommentDaoIntf{
    private Connection connection;
    private PreparedStatement ps;
    private String sql;
    ResultSet rs;


    public List<Comment> getCommentByIduser(int iduser){
        sql = "SELECT * FROM commentsInfo WHERE iduser =?";
        return findBy(sql, iduser);
    }

    public List<Comment> getCommentByIdproduct(int idproduct) {
        sql = "SELECT * FROM commentsInfo WHERE idproduct =?";
        return findBy(sql, idproduct);
    }

    public List<Comment> findBy(String sql, int id ){
        connection =  DBConnector.getDBConn();

        try {

            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            List<Comment> comments = new ArrayList<>();
            while (rs.next()) {
                Comment comm = new Comment();
                comm.setIduser(rs.getInt("iduser"));
                comm.setIdproduct(rs.getInt("idproduct"));
                comm.setClevel(rs.getInt("clevel"));
                comm.setCtitle(rs.getString("ctitle"));
                comm.setCcontent(rs.getString("ccontent"));
                comm.setBought(rs.getBoolean("isBought"));
                comments.add(comm);
            }
            rs.next();
            return comments;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            //关闭数据库连接
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean setComment(int idproduct, int iduser, int clevel , String ctitle, String ccomment){
        if(clevel < 1 || clevel > 5){
            return false;
        }
        connection  = DBConnector.getDBConn();
        sql  = "INSERT INTO `comments` (`idproduct`, `iduser`, `clevel`, `ctitle`, `ccontent`) " +
                "VALUES (?, ?, ?, ?, ?);";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idproduct);
            ps.setInt(2,iduser);
            ps.setInt(3, clevel);
            ps.setString(4, ctitle);
            ps.setString(5, ccomment);
            return (ps.executeUpdate() == 1) ? true :  false;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;

        }
        finally {
            //关闭数据库连接
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}

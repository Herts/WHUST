package whustore.dao;

import whustore.model.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao implements CommentDaoIntf {
    private Connection connection;
    private PreparedStatement ps;
    private String sql;
    ResultSet rs;


    public List<Comment> getCommentByIduser(int iduser) {
        sql = "SELECT * FROM commentsInfo WHERE iduser =?";
        return findBy(sql, iduser);
    }

    public List<Comment> getCommentByIdproduct(int idproduct) {
        sql = "SELECT * FROM commentsInfo WHERE idproduct =?";
        return findBy(sql, idproduct);
    }

    private List<Comment> findBy(String sql, int id) {
        connection = DBConnector.getDBConn();

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
        } finally {
            //关闭数据库连接
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public Comment getCommentByIduserAndIdproduct(int iduser, int idproduct) {
        connection = DBConnector.getDBConn();
        sql = "SELECT * FROM commentsInfo WHERE iduser = ? AND idproduct=?";
        try {

            ps = connection.prepareStatement(sql);
            ps.setInt(1, iduser);
            ps.setInt(2, idproduct);
            rs = ps.executeQuery();
            Comment comm = new Comment();
            if (rs.next()) {

                comm.setIduser(rs.getInt("iduser"));
                comm.setIdproduct(rs.getInt("idproduct"));
                comm.setClevel(rs.getInt("clevel"));
                comm.setCtitle(rs.getString("ctitle"));
                comm.setCcontent(rs.getString("ccontent"));
                comm.setBought(rs.getBoolean("isBought"));

            }
            rs.next();
            return comm;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            //关闭数据库连接
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean setComment(int idproduct, int iduser, int clevel, String ctitle, String ccontent, Boolean isCommented) {
        if (!isCommented) {
            return insertComment(idproduct, iduser, clevel, ctitle, ccontent);
        }
        return updateComment(idproduct, iduser, clevel, ctitle, ccontent);
    }

    public boolean insertComment(int idproduct, int iduser, int clevel, String ctitle, String ccontent) {
        sql = "INSERT INTO `comments` (`idproduct`, `iduser`, `clevel`, `ctitle`, `ccontent`) " +
                "VALUES (?, ?, ?, ?, ?);";
        return updateExe(idproduct, iduser, clevel, ctitle, ccontent, sql);
    }

    public boolean updateComment(int idproduct, int iduser, int clevel, String ctitle, String ccontent) {

        if (clevel < 1 || clevel > 5) {
            return false;
        }
        sql = "UPDATE comments SET clevel = ?, ctitle = ?, ccontent = ? WHERE iduser = ? AND idproduct = ?";
        connection = DBConnector.getDBConn();

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(5, idproduct);
            ps.setInt(4, iduser);
            ps.setInt(1, clevel);
            ps.setString(2, ctitle);
            ps.setString(3, ccontent);
            return (ps.executeUpdate() == 1);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            //关闭数据库连接
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean updateExe(int idproduct, int iduser, int clevel, String ctitle, String ccontent, String sql) {
        if (clevel < 1 || clevel > 5) {
            return false;
        }
        connection = DBConnector.getDBConn();

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idproduct);
            ps.setInt(2, iduser);
            ps.setInt(3, clevel);
            ps.setString(4, ctitle);
            ps.setString(5, ccontent);
            return (ps.executeUpdate() == 1);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
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

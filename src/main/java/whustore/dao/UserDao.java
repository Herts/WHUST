package whustore.dao;

import whustore.model.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private Connection conn = null;
    private Context context = null;
    private DataSource dataSource = null;

    public boolean passwordIsCorrect(User user) {

        //获取数据库连接
        conn = DBConnector.getDBConn();
        if (conn == null)
            return false;

        String sql = "SELECT * FROM user WHERE username = ?";

        try {//查询数据库
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            rs.next();
            String userPw = rs.getString("password");
            if (user.getPassword().equals(userPw))
                return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean userReg(User user) {
        conn = DBConnector.getDBConn();
        if (conn == null)
            return false;
        String sql = "SELECT COUNT(*) FROM user WHERE username = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                return false;
            } else {
                sql = "INSERT INTO user (username, password, email, phone) VALUES (?, ?, ?, ?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPhone());
                ps.executeUpdate();
                return true;
            }
            //System.out.println(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            //关闭数据库连接
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

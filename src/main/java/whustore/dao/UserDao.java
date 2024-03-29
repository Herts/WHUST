package whustore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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

@Repository
public class UserDao {

    private Connection conn = null;
    private Context context = null;
    private DataSource dataSource = null;

    public User loginCheck(User user) {

        //获取数据库连接
        conn = DBConnector.getDBConn();
        if (conn == null)
            return null;

        String sql = "SELECT * FROM user WHERE username = ?";

        try {//查询数据库
            User result;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            rs.next();
            String userPw = rs.getString("password");
            if (user.getPassword().equals(userPw)) {
                result = new User();
                result.setUserid(rs.getInt("iduser"));
                result.setEmail(rs.getString("email"));
                result.setPhone(rs.getString("phone"));
                result.setUsername(rs.getString("username"));
                result.setPassword(rs.getString("password"));
                return result;
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return null;
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

    public boolean userModify(User user) {
        //获取数据库连接
        conn = DBConnector.getDBConn();
        if (conn == null)
            return false;

        String sql = "UPDATE user SET email = ?, phone = ? WHERE username = ?";

        try {//查询数据库
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPhone());
            ps.setString(3, user.getUsername());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

}

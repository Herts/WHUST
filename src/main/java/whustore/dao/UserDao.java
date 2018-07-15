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
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    private Connection conn = null;
    private Context context = null;
    private DataSource dataSource = null;

    /**
     * 登陆密码检查
     *
     * @param user
     * @return
     */
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
        finally {
            //关闭数据库连接
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
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

    /**
     * 更改用户的记录
     *
     * @param user
     * @return
     */
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
    /**
     * 检查用户是否属于某个team
     * @param iduser
     * @param idteam
     * @return
     */
    public boolean checkTeamid (int iduser, int idteam){
        conn = DBConnector.getDBConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Integer> iduserinteam = new ArrayList<>();
        String sql = "SELECT * FROM team WHERE idteam = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1,idteam);
            rs = ps.executeQuery();
            while(rs.next()){
                iduserinteam.add(rs.getInt("iduser"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(iduserinteam.contains(iduser)){
            return true;
        }
        else {
            return false;
        }
    }
}

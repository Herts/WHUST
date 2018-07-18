package whustore.dao;

import org.springframework.stereotype.Repository;
import whustore.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao implements UserDaoIntf{
    private Connection conn = null;
    private String sql;
    private PreparedStatement ps;
    private ResultSet rs;


    /**
     * 用户注册
     *
     * @param user 传入用户
     * @return 返回是否插入
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

    public User getUserByIduser(int iduser){
        sql = "SELECT * FROM user WHERE iduser = ?";
        return  findBy(iduser, sql);
    }

    public List<User> getAllUser(){
        List<User> userList= new ArrayList<>();
        conn = DBConnector.getDBConn();
        sql = "SELECT * FROM user";
        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                User u = new User();
                u.setUserid(rs.getInt("iduser"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                userList.add(u);
            }
            rs.next();
            return userList;

        }
        catch (SQLException e){

            e.printStackTrace();
            return null;
        }
    }



    public <T> User findBy(T key, String sql){
        conn = DBConnector.getDBConn();
        if (conn == null)
            return null;
        try {//查询数据库

            ps = conn.prepareStatement(sql);
            ps.setObject(1, key);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                User result = new User();
                result.setUserid(rs.getInt("iduser"));
                result.setEmail(rs.getString("email"));
                result.setPhone(rs.getString("phone"));
                result.setUsername(rs.getString("username"));
                result.setPassword(rs.getString("password"));
                return result;
            }
             else return null;
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

    public User getUserByUsername(String username){
        sql = "SELECT * FROM user WHERE username = ?";
        return findBy(username, sql);
    }

    /**
     * 登陆密码检查
     *
     * @param loginUser
     * @return
     */
    public User loginCheck(User loginUser) {


        User findUser = getUserByUsername(loginUser.getUsername());
        if (loginUser.getPassword().equals(findUser.getPassword())) {
            return findUser;
        }
        return null;
    }

    public boolean deleteUserByIduser(int iduser){
        sql = "DELETE FROM user WHERE iduser = ?";
        return deleteUserBy(iduser, sql);


    }

    public <T> boolean deleteUserBy(T key, String sql){
        conn = DBConnector.getDBConn();
        try{
            ps = conn.prepareStatement(sql);
            ps.setObject(1, key);
            return ps.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

}

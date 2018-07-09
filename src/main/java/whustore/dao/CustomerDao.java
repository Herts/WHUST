package whustore.dao;

import org.springframework.stereotype.Repository;
import whustore.model.Customer;
import whustore.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CustomerDao {

    Connection conn;

    public Customer getCustomer(User user) {

        //获取数据库连接
        conn = DBConnector.getDBConn();
        if (conn == null)
            return null;
        Customer cus = new Customer();

        String sql = "SELECT * FROM user u,customer c WHERE  u.username = ? AND u.iduser = c.iduser";

        try {//查询数据库
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            rs.next();
            cus.setEmail(rs.getString("email"));
            cus.setPhone(rs.getString("phone"));
            cus.setAddress(rs.getString("addr"));
            cus.setDate(rs.getDate("birthdate").toString());
            cus.setFname(rs.getString("fname"));
            cus.setLname(rs.getString("lname"));
            cus.setSex(rs.getString("sex"));
            return cus;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean modifyCustomer(Customer customer, User user) {
        conn = DBConnector.getDBConn();
        if (conn == null)
            return false;
        user.setEmail(customer.getEmail());
        user.setPhone(customer.getPhone());
        syncWithUser(user);
        String username = user.getUsername();

        String sql = "UPDATE customer SET fname = ?, lname = ?, sex = ?,birthdate = ?,addr = ? WHERE iduser =(SELECT iduser FROM user WHERE username = ?)";

        try {//查询数据库
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, customer.getFname());
            ps.setString(2,customer.getLname());
            ps.setString(3,customer.getSex());
            ps.setString(4,customer.getDate());
            ps.setString(5,customer.getAddress());
            ps.setString(6,username);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean syncWithUser(User user) {
        UserDao userDao = new UserDao();
        if (userDao.userModify(user))
            return true;
        else return false;
    }
}

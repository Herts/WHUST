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
            cus.setUser(user);
            cus.setAddress(rs.getString("addr"));
            cus.setDate(rs.getDate("birthdate"));
            cus.setFname(rs.getString("fname"));
            cus.setLname(rs.getString("lname"));
            cus.setSex(rs.getString("sex"));

            return cus;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

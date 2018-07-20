package whustore.dao;

import org.springframework.stereotype.Repository;
import whustore.Hakari.HakariDB;
import whustore.model.Customer;
import whustore.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CustomerDao {


    /**
     * 获取Customer
     *
     * @param user 用户对象
     * @return Customer对象
     */
    public Customer getCustomer(User user) {
        String sql = "SELECT * FROM user u,customer c WHERE  u.username = ? AND u.iduser = c.iduser";

        try (Connection conn = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {//查询数据库
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            Customer cus;
            if (rs.next()) {
                cus = new Customer();
                cus.setEmail(user.getEmail());
                cus.setPhone(user.getPhone());
                cus.setAddress(rs.getString("addr"));
                if (rs.getDate("birthdate") != null)
                    cus.setDate(rs.getDate("birthdate").toString());
                cus.setFname(rs.getString("fname"));
                cus.setLname(rs.getString("lname"));
                cus.setSex(rs.getString("sex"));
                return cus;
            } else {
                cus = new Customer();
                insertCustomer(cus, user);
                cus.setEmail(user.getEmail());
                cus.setPhone(user.getPhone());
                return cus;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 当没有插入customer信息时插入该信息
     *
     * @param customer Customer对象
     * @param user 用户
     * @return 是否成功
     */
    public boolean insertCustomer(Customer customer, User user) {
        String sql = "INSERT INTO customer (fname, lname, sex, birthdate, addr, tel, email,iduser) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, customer.getFname());
            ps.setString(2, customer.getLname());
            ps.setString(3, customer.getSex());
            ps.setString(4, customer.getDate());
            ps.setString(5, customer.getAddress());
            ps.setString(6, user.getPhone());
            ps.setString(7, user.getEmail());
            ps.setInt(8, user.getUserid());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改Customer的记录
     *
     * @param customer
     * @param user
     * @return
     */
    public boolean modifyCustomer(Customer customer, User user) {
        user.setEmail(customer.getEmail());
        user.setPhone(customer.getPhone());
        syncWithUser(user);
        String username = user.getUsername();

        String sql = "UPDATE customer SET fname = ?, lname = ?, sex = ?,birthdate = ?,addr = ? WHERE iduser = ?";

        try (Connection conn = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {//修改数据库
            ps.setString(1, customer.getFname());
            ps.setString(2, customer.getLname());
            ps.setString(3, customer.getSex());
            ps.setString(4, customer.getDate());
            ps.setString(5, customer.getAddress());
            ps.setInt(6, user.getUserid());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 与user记录同步
     *
     * @param user
     * @return
     */
    private boolean syncWithUser(User user) {
        UserDao userDao = new UserDao();
        if (userDao.userModify(user))
            return true;
        else return false;
    }
}

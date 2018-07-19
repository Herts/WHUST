package whustore.dao;

import whustore.Hakari.HakariDB;
import whustore.data.RandomNumber;
import whustore.model.Customer;
import whustore.model.CustomerInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerInfoDao implements CustomerInfoDaoIntf{
    private String sql;
    private ResultSet rs;


    /**
     * @param name 姓名
     * @param addr 地址
     * @param tel 电话
     * @param iduser 用户ID
     * @return 用户信息的ID
     */
    public int setCustomerInfo(String name, String addr, String tel, int iduser) {
        sql = "INSERT INTO customerInfo(name, addr, tel, iduser, idcustomerInfo) VALUES(?, ?, ?, ?, ?)";

        try(Connection connection = HakariDB.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, name);
            ps.setString(2, addr);
            ps.setString(3, tel);
            ps.setInt(4, iduser);
            int random = RandomNumber.getRandomNumber();
            ps.setInt(5, random);
            while (ps.executeUpdate() != 1){
                random = RandomNumber.getRandomNumber();
                ps.setInt(5, random);
            }
            return random;
        }
        catch (SQLException e){
            e.printStackTrace();
            return -1;
        }

    }

    /**
     * @param name 姓名
     * @param addr 地址
     * @param tel 电话
     * @param iduser 用户ID
     * @return 用户信息的ID
     */
    public int isCustomerInfoUnique(String name, String addr, String tel, int iduser){
        sql = "SELECT * WHERE name = ? AND addr = ? AND tel = ? AND iduer = ?";
        try (Connection connection = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, name);
            ps.setString(2, addr);
            ps.setString(3, tel);
            ps.setInt(4, iduser);
            rs = ps.executeQuery();
            if( rs.next() || rs.getInt("iduser")== iduser){
                return rs.getInt("idcustomerInfo");
            }
            return -1;
        }
        catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * @param iduser 用户ID
     * @return 最新的CustomerInfo
     */
    public CustomerInfo getLastCustomerInfoByIduser(int iduser){
        sql = "SELECT * FROM customerInfo WHERE iduser = ?  ORDER BY createsince";
        try (Connection connection = HakariDB.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, iduser);
            rs = ps.executeQuery();
            if(rs.next()){
                CustomerInfo info = new CustomerInfo();
                info.setIdCustomerInfo(rs.getInt("idcustomerInfo"));
                info.setAddr(rs.getString("addr"));
                info.setName(rs.getString("name"));
                info.setTel(rs.getString("tel"));
                return info;
            }
            return null;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

   public int initializeCustomerInfo(Customer customer, int iduser){
        String name = customer.getFname() + " " + customer.getLname();
        return setCustomerInfo(name, customer.getAddress(), customer.getPhone(), iduser);
   }

}

package whustore.dao;

import whustore.model.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class UserDao {

    public boolean passwordIsCorrect(User user)
    {
        DataSource ds = new ComboPooledDataSource();

        try {
            Connection conn = ds.getConnection();
            System.out.println("连接上了");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}

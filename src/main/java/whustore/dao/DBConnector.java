package whustore.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnector {
    private static Context context = null;
    private static DataSource dataSource = null;

    /*
     * 获取数据库的连接
     * 记得在Dao里面 close*/
    public static Connection getDBConn() {
        Connection conn;
        try {
            if (context == null)
                context = new InitialContext();
            if (dataSource == null)
                dataSource = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
            conn = dataSource.getConnection();
            return conn;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取连接失败");
            return null;
        }
    }
}

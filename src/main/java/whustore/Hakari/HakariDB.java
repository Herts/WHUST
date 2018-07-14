package whustore.Hakari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


import java.sql.Connection;
import java.sql.SQLException;

public class HakariDB {
    // Examines both filesystem and classpath for .properties file
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    static {
        config.setJdbcUrl( "jdbc:mysql://104.199.177.163:3306/WHUStore" );
        config.setUsername( "springtest" );
        config.setPassword( "wsspring" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );
    }




    public static Connection getConnection(){
        HikariConfig config = new HikariConfig("/Hakari/hikari.properties");
        HikariDataSource ds = new HikariDataSource(config);
        Connection connection = null;
        try{
            connection = ds.getConnection();
            return connection;
        }
        catch (SQLException e){
            try{
                connection.rollback();
            }
            catch (SQLException e1){
                e1.printStackTrace();
                return null;
            }
            e.printStackTrace();
            return null;


    }
    }




}

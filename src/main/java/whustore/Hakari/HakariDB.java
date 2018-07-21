package whustore.Hakari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


import javax.sql.DataSource;


public class HakariDB {

    // Examines both filesystem and classpath for .properties file
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl( "jdbc:mysql://104.199.177.163:3306/WHUStore?useSSL=false" );
        config.setUsername( "springtest" );
        config.setPassword( "wsspring" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );
    }

    public static DataSource getDataSource(){
        return ds;
    }

}

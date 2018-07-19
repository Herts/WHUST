package whustore.dao;

import whustore.model.UserRecord;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UserRecordDao {
    private FavDao dao = new FavDao();

    private Connection conn;

    public boolean saveUserRecord(UserRecord userRecord) {
        saveSearchRecord(userRecord.getSearchRecord(), userRecord.getUserId());
        saveCateRecord(userRecord.getFiltedCates(), userRecord.getUserId());
        return true;
    }

    public UserRecord loadRecord(int userId) {
        try {
            UserRecord userRecord = new UserRecord();
            userRecord.setUserId(userId);
            if (conn == null || conn.isClosed()) {
                conn = DBConnector.getDBConn();
            }
            String sql = "SELECT * FROM usersearch WHERE iduser=" + userId;
            assert conn != null;
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<String> searchInfos = new ArrayList<>();
            while (rs.next()) {
                searchInfos.add(rs.getString("info"));
            }
            userRecord.setSearchRecord(searchInfos);

            sql = "SELECT * FROM usercatehis WHERE iduser=" + userId;
            rs = conn.createStatement().executeQuery(sql);
            Map<String, Integer> cateMap = new TreeMap<>();
            while (rs.next()) {
                cateMap.put(rs.getString("info"), rs.getInt("times"));
            }
            userRecord.setFiltedCates(cateMap);
            return userRecord;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存搜索的记录
     */
    private boolean saveSearchRecord(List<String> searInfos, int userId) {
        //删除愿有记录
        String sql = "DELETE FROM usersearch WHERE iduser=" + userId;
        try {
            if (conn == null || conn.isClosed())
                conn = DBConnector.getDBConn();
            assert conn != null;
            conn.createStatement().executeUpdate(sql);
            StringBuilder sqlBuilder = new StringBuilder();
            for (String info :
                    searInfos) {
                sqlBuilder.append("INSERT INTO usersearch (iduser, info) VALUES (").append(userId).append(", '").append(info).append("');");
            }
            //添加新纪录
            sql = sqlBuilder.toString();
            conn.createStatement().executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存按类别检索的记录
     */
    private boolean saveCateRecord(Map<String, Integer> cateRecord, int userId) {
        //删除原纪录
        String sql = "DELETE FROM usercatehis WHERE iduser=" + userId;
        try {
            if (conn == null || conn.isClosed())
                conn = DBConnector.getDBConn();
            assert conn != null;
            conn.createStatement().executeUpdate(sql);
            StringBuilder sqlBuilder = new StringBuilder();
            for (String info :
                    cateRecord.keySet()) {
                sqlBuilder.append("INSERT INTO usercatehis (iduser, info, times) VALUES (").append(userId).append(", '").append(info).append("', ").append(cateRecord.get(info)).append(");");
            }
            sql = sqlBuilder.toString();
            //添加新纪录
            conn.createStatement().executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

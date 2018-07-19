package whustore.dao;

import whustore.data.ProductData;
import whustore.model.UserRecord;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            //读取搜索记录
            String sql = "SELECT * FROM usersearch WHERE iduser=" + userId;
            assert conn != null;
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<String> searchInfos = new ArrayList<>();
            while (rs.next()) {
                searchInfos.add(rs.getString("info"));
            }
            userRecord.setSearchRecord(searchInfos);

            //读取分类检索记录
            sql = "SELECT * FROM usercatehis WHERE iduser=" + userId;
            rs = conn.createStatement().executeQuery(sql);
            Map<String, Integer> cateMap = new TreeMap<>();
            while (rs.next()) {
                cateMap.put(rs.getString("info"), rs.getInt("times"));
            }
            userRecord.setFiltedCates(cateMap);
            //读取收藏列表记录
            dao.getIdproductByIduser(userId);
            StringBuilder favProductNames = new StringBuilder();
            for (int id:
                 dao.getIdproductByIduser(userId)) {
                favProductNames.append(ProductData.getProductByID(id).getProductName());
            }
            userRecord.setFavProductNames(favProductNames.toString());
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
            Statement statement = conn.createStatement();
            for (String info :
                    searInfos) {
                sqlBuilder.append("INSERT INTO usersearch (iduser, info) VALUES (").append(userId).append(", '").append(info).append("');");
                statement.addBatch(sqlBuilder.toString());
                sqlBuilder = new StringBuilder();
            }
            //添加新纪录
            statement.executeBatch();
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
            Statement statement = conn.createStatement();
            for (String info :
                    cateRecord.keySet()) {
                sqlBuilder.append("INSERT INTO usercatehis (iduser, info, times) VALUES (").append(userId).append(", '").append(info).append("', ").append(cateRecord.get(info)).append(");");
                statement.addBatch(sqlBuilder.toString());
                sqlBuilder = new StringBuilder();
            }
            //添加新纪录
            statement.executeBatch();
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

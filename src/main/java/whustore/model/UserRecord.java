package whustore.model;

import whustore.dao.UserRecordDao;
import whustore.data.UserRecordData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UserRecord {

    private int userId;

    private int changeTimes = 0;

    private UserRecordDao dao = new UserRecordDao();

    /**
     * 搜索过的信息的记录
     */
    private List<String> searchRecord = new ArrayList<>();

    /**
     * 进行分类搜索过后的
     */
    private Map<String, Integer> filterCate = new TreeMap<>();

    public void addSearchInfo(String info) {
        this.searchRecord.add(info);
        changeTimes++;
        upDate();
    }

    public void addCateFiltered(String cate) {
        for (String string :
                filterCate.keySet()) {
            if (string.equals(cate)) {
                int oldTimes = filterCate.get(string);
                filterCate.remove(string);
                filterCate.put(string, oldTimes + 1);
                return;
            }
        }
        filterCate.put(cate, 1);
        changeTimes++;
        upDate();
    }

    public List<String> getSearchRecord() {
        return searchRecord;
    }

    public void setSearchRecord(List<String> searchRecord) {
        this.searchRecord = searchRecord;
    }

    public Map<String, Integer> getFiltedCates() {
        return filterCate;
    }

    public void setFiltedCates(Map<String, Integer> filterCate) {
        this.filterCate = filterCate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private void upDate() {
        if (changeTimes > 2) {
            dao.saveUserRecord(this);
        }
    }
}

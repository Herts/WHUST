package whustore.data;

import whustore.model.UserRecord;

import java.util.ArrayList;
import java.util.List;

public class UserRecordData {

    private static List<UserRecord> userRecordList = new ArrayList<>();

    public static List<UserRecord> getUserRecordList() {
        return userRecordList;
    }

    public static void setUserRecordList(List<UserRecord> userRecordList) {
        UserRecordData.userRecordList = userRecordList;
    }

    public static UserRecord getUserRecord(int userId) {
        for (UserRecord userRecord :
                userRecordList) {
            if (userRecord.getUserId() == userId)
                return userRecord;
        }
        return null;
    }

    public static void addUserRecord(UserRecord userRecord) {
        userRecordList.add(userRecord);
    }

    public static void removeUserRecord(int userId) {
        for (UserRecord userRecord :
                userRecordList) {
            if (userRecord.getUserId() == userId)
            {
                userRecordList.remove(userRecord);
                break;
            }
        }
    }
}

package whustore.service;

import org.springframework.stereotype.Service;
import whustore.dao.UserDao;
import whustore.model.User;

import java.util.List;

@Service
public class UserService implements UserServiceIntf {
    UserDao userDao = new UserDao();
    /**
     * 登陆检查
     *
     * @param user
     * @return
     */
    public User loginCheck(User user) {
        UserDao userDao = new UserDao();
        return userDao.loginCheck(user);
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    public boolean userReg(User user) {

        return userDao.userReg(user);
    }
    public boolean checkTeamid (int iduser, int idteam){
        UserDao ud = new UserDao();
        return ud.checkTeamid(iduser, idteam);
    }

    public List<User> getAllUser(){
        return userDao.getAllUser();
    }

    public User getUserByIduser(int iduser){
        return userDao.getUserByIduser(iduser);
    }

    public User getUserByUsername(String username){
        return userDao.getUserByUsername(username);
    }

    public boolean isUsernameUnique(String username){
        if(getUserByUsername(username) == null) {
            return true;
        }
        return false;
    }

    public void updateUser(User user){
        userDao.userModify(user);
    }

    public boolean deleteUserByIduser(int iduser) {
        return userDao.deleteUserByIduser(iduser);
    }
}

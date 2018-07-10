package whustore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whustore.dao.UserDao;
import whustore.model.User;

@Service
public class UserService {

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
        UserDao userDao = new UserDao();
        return userDao.userReg(user);
    }
}

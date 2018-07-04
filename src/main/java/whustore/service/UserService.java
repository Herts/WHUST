package whustore.service;

import whustore.dao.UserDao;
import whustore.model.User;

public class UserService {
    public boolean passwordIsCorrect(User user)
    {
        UserDao userDao = new UserDao();
        return userDao.passwordIsCorrect(user);
    }
}

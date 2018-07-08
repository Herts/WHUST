package whustore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whustore.dao.UserDao;
import whustore.model.User;
@Service
public class UserService {

    public boolean passwordIsCorrect(User user)
    {
        UserDao userDao = new UserDao();
        return userDao.passwordIsCorrect(user);
    }

    public boolean userReg(User user)
    {
        UserDao userDao = new UserDao();
        return userDao.userReg(user);
    }
}

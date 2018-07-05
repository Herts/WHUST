import whustore.dao.UserDao;
import whustore.model.User;

public class test {
    public static void main(String[] args) {
        System.out.println("这是一个测试类");
        UserDao userDao = new UserDao();
        User user = new User();
        user.setUsername("huhaomewng");
        user.setPassword("mima");
        userDao.passwordIsCorrect(user);
    }
}

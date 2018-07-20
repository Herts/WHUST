package whustore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whustore.dao.OrderDao;
import whustore.dao.UserDao;
import whustore.dao.UserRecordDao;
import whustore.data.UserRecordData;
import whustore.model.*;

import java.util.*;


@Service
public class UserService implements UserServiceIntf {
    @Autowired
    OrderService orderService = new OrderService();

    UserDao userDao = new UserDao();

    /**
     * 登陆检查
     *
     * @param user 用户
     * @return 是否成功
     */
    public User loginCheck(User user) {
        UserDao userDao = new UserDao();
        return userDao.loginCheck(user);
    }

    /**
     * 用户注册
     *
     * @param user 用户
     * @return 是否成功
     */
    public boolean userReg(User user) {

        return userDao.userReg(user);
    }

    /**
     * 验证团队id
     *
     * @param iduser 用户艾迪
     * @param idteam 团队艾迪
     * @return 是否成功
     */
    public boolean checkTeamid(int iduser, int idteam) {
        UserDao ud = new UserDao();
        return ud.checkTeamid(iduser, idteam);
    }

    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    public User getUserByIduser(int iduser) {
        return userDao.getUserByIduser(iduser);
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public boolean isUsernameUnique(String username) {
        return getUserByUsername(username) == null;
    }

    public void updateUser(User user) {
        userDao.userModify(user);
    }

    public boolean deleteUserByIduser(int iduser) {
        return userDao.deleteUserByIduser(iduser);
    }

    /**
     * 获取用户购买过的产品的数量纪录
     *
     * @return 产品和数量的Map
     */
    public Map<Product, Integer> getUserProductRecords(User user,
                                                       Num orderSize,
                                                       Map<Date, Double> totalGrow,
                                                       Map<Date, Integer[]> orderGrow) {

        Map<Product, Integer> resultProductNums = new HashMap<>();

        double total = 0;
        int totalOrder = 0;
        int totalProduct = 0;
        int orderNum;
        int num;

        List<Order> orderList = orderService.getAllOrderByIduser(user.getUserid());
        orderSize.setINT(orderList.size());
        for (int j = orderList.size() - 1; j >= 0; j--) {
            Order order = orderList.get(j);
            //花费增长和订单增长计算区
            Date createDate = order.getCreateDate();
            total += order.getTotal();
            totalOrder++;
            totalProduct += order.getItems().size();
            Integer[] newArrayData = {totalOrder, totalProduct};
            if (totalGrow.get(createDate) == null) {
                //该日期没有订单
                totalGrow.put(createDate, total);
            } else {
                //该日期已有订单
                totalGrow.remove(createDate);
                totalGrow.put(createDate, total);
            }
            if (orderGrow.get(createDate) == null) {
                //该日期没有放入订单数
                orderGrow.put(createDate, newArrayData);
            } else {
                //该日期已有订单数
                orderGrow.remove(createDate);
                orderGrow.put(createDate, newArrayData);
            }
            //遍历每一个订单
            Map<Product, Integer> orderNumMap = order.getItems();
            Set<Product> orderItems = order.getItems().keySet();
            for (Product product :
                    orderItems) {
                if (product == null) continue;
                if (resultProductNums.get(product) != null) {
                    //如果结果Map里面已经有这个产品则把订单的产品数加上去
                    orderNum = orderNumMap.get(product);
                    num = resultProductNums.get(product);
                    resultProductNums.remove(product);
                    resultProductNums.put(product, num + orderNum);
                } else {
                    //结果Map里尚没有这个产品，把订单的产品放入Map
                    resultProductNums.put(product, orderNumMap.get(product));
                }
            }
        }
        return resultProductNums;
    }


    public UserRecord getUserRecord(int userId) {
        UserRecordDao userRecordDao = new UserRecordDao();
        return userRecordDao.loadRecord(userId);
    }

}

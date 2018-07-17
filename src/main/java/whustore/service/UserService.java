package whustore.service;

import org.springframework.stereotype.Service;
import whustore.dao.OrderDao;
import whustore.dao.UserDao;
import whustore.model.Num;
import whustore.model.Order;
import whustore.model.Product;
import whustore.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserService {

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
        UserDao userDao = new UserDao();
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

    /**
     * 获取用户购买过的产品的数量纪录
     *
     * @return 产品和数量的Map
     */
    public Map<Product, Integer> getUserProductRecords(User user, Num orderSize) {
        Map<Product, Integer> resultProductNums = new HashMap<>();
        OrderDao orderDao = new OrderDao();
        int num = 0;
        int orderNum = 0;
        List<Order> orderList = orderDao.getOrderlist(user.getUserid());
        orderSize.setINT(orderList.size());
        for (Order order :
                orderList) {
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


}

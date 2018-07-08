package whustore.cont;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import whustore.dao.CartDao;
import whustore.dao.CustomerDao;
import whustore.dao.OrderDao;
import whustore.dao.UserDao;
import whustore.model.*;

import java.util.List;

@Controller
public class TestController {

    @RequestMapping("Test")
    public String test()
    {
        CustomerDao customerDao = new CustomerDao();
        User user = new User();
        user.setUsername("huhaomeng");
        user.setEmail("111@outlook.com");
        customerDao.getCustomer(user);
        return "homepage";
    }
    @RequestMapping("TestOrder")
    public String testorder(){

        OrderDao dao = new OrderDao();
        List<Order> orderlist = dao.getOrderlist(1);
        return null;
    }

}

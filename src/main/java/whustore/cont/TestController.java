package whustore.cont;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import whustore.dao.CartDao;
import whustore.dao.OrderDao;
import whustore.model.*;

import java.util.List;

@Controller
public class TestController {

    @RequestMapping("Test")
    public String test()
    {
        CartDao cartDao = new CartDao();
        Cart cart = cartDao.getUserCart(1);
        return null;
    }
    @RequestMapping("TestOrder")
    public String testorder(){

        OrderDao dao = new OrderDao();
        List<Order> orderlist = dao.getOrderlist(1);
        return null;
    }

}

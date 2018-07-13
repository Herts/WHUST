package whustore.cont;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import whustore.dao.OrderDao;
import whustore.model.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {

    @RequestMapping("addorder")
    public String addOrder (HttpServletRequest request){
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        Order order = new Order();
        order.setIduser(cart.getUserID());
        order.setItems(cart.getItems());
        order.setIdOrder((int)System.currentTimeMillis()/1000);
        OrderDao od = new OrderDao();
        boolean isAdd = false;
        isAdd = od.addOrder(order,cart.getCartID());
        if(isAdd){
            return "home";
        }
        else{
            return "home";
        }
    }

}

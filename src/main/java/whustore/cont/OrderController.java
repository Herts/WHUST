package whustore.cont;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import whustore.dao.OrderDao;
import whustore.model.*;
import whustore.service.CartService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {

    @RequestMapping("addorder")
    public String addOrder (HttpServletRequest request,
                            ModelMap modelMap){
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        Order order = new Order();
        order.setIduser(cart.getUserID());
        order.setItems(cart.getItems());
        order.setIdOrder((int)System.currentTimeMillis()/1000);
        OrderDao od = new OrderDao();
        boolean isAdd = false;
        isAdd = od.addOrder(order,cart.getCartID());
        if(isAdd){
            User user = (User) request.getSession().getAttribute("user");
            CartService service = new CartService();
            Cart userCart = service.getUserCart(user.getUserid());
            request.getSession().setAttribute("cart",userCart);
            modelMap.addAttribute("order",order);
            modelMap.addAttribute("items",order.getItems().keySet());
            return "homepage";
        }
        else{
            return "homepage";
        }
    }

}

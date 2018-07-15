package whustore.cont;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import whustore.dao.OrderDao;
import whustore.model.*;
import whustore.service.CartService;
import whustore.service.CustomerService;
import whustore.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrderController {
    OrderService service = new OrderService();

    @RequestMapping("addorder")
    public String addOrder(HttpServletRequest request,
                           ModelMap modelMap) {
        String name;
        String address;
        String phone;
        //获取收货信息
        if (request.getParameter("name") != null) {
            name = request.getParameter("name");
            address = request.getParameter("address");
            phone = request.getParameter("phone");
        } else {
            CustomerService cs = new CustomerService();
            Customer customer = cs.getCustomer((User) request.getSession().getAttribute("user"));
            name = customer.getLname() + customer.getFname();
            address = customer.getAddress();
            phone = customer.getPhone();
        }
        //设置cart
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        Order order = new Order();
        order.setIduser(cart.getUserID());
        order.setItems(cart.getItems());
        order.setIdOrder((int) System.currentTimeMillis() / 1000);
        order.setName(name);
        order.setAddress(address);
        order.setPhone(phone);
        OrderDao od = new OrderDao();
        //数据库操作
        boolean isAdd = false;
        isAdd = od.addOrder(order, cart.getCartID());
        if (isAdd) {
            User user = (User) request.getSession().getAttribute("user");
            CartService service = new CartService();
            Cart userCart = service.getUserCart(user.getUserid());
            request.getSession().setAttribute("cart", userCart);
            modelMap.addAttribute("order", order);
            modelMap.addAttribute("items", order.getItems().keySet());
            return "order/order";
        } else {
            return "order/order";
        }
    }

    @RequestMapping("order/myOrders")
    public String myOrders(HttpServletRequest request,
                           ModelMap modelMap) {
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getUserid();
        List<Order> orderList = service.getUserOrderList(userId);
        modelMap.addAttribute("orders", orderList);
        return "order/myOrders";
    }

}

package whustore.cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import whustore.model.*;
import whustore.service.CartService;
import whustore.service.CustomerInfoService;
import whustore.service.CustomerService;
import whustore.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerInfoService infoService;

    @Autowired
    CartService cartService;

    @RequestMapping("addorder")
    public String addOrder(HttpServletRequest request,
                           ModelMap modelMap) {
        User u = (User) request.getSession().getAttribute("user");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        String name, address, phone;
        CustomerInfo info;
        //获取收货信息
        if (request.getParameter("name") != null) {
            name = request.getParameter("name");
            address = request.getParameter("address");
            phone = request.getParameter("phone");
            info = infoService.setAndGetCustomerInfo(name, address, phone, u.getUserid());
        } else {
            info = infoService.getLastCustomerInfoByIduser(u.getUserid());
            if (info == null) {
                Customer customer = customerService.getCustomer(u);
                info = infoService.initAndGetCustomerInfo(customer, u.getUserid());
            }
        }
        Order order = orderService.addOrder(cart, info);

        if (order != null) {
            Cart userCart = cartService.getUserCart(u.getUserid());
            request.getSession().setAttribute("cart", userCart);
            request.getSession().setAttribute("order", order);
            modelMap.addAttribute("info", info);
            modelMap.addAttribute("order", order);
            modelMap.addAttribute("items", order.getItems().keySet());
        }
        return "order/order";
    }


    @RequestMapping("order/myOrders")
    public String myOrders(HttpServletRequest request,
                           ModelMap modelMap) {
        User user = (User) request.getSession().getAttribute("user");
        List<Order> orderList = orderService.getAllOrderByIduser(user.getUserid());

        modelMap.addAttribute("orders", orderList);
        return "order/myOrders";
    }

}

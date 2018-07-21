package whustore.cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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
        List<Order> orderList = orderService.addOrderByIdTeam(cart, info);

        if (orderList != null) {
            Cart userCart = cartService.getUserCart(u.getUserid());
            request.getSession().setAttribute("cart", userCart);
            request.getSession().setAttribute("orderList", orderList);
            modelMap.addAttribute("info", info);
            modelMap.addAttribute("orderList", orderList);
//            modelMap.addAttribute("items", order.getItems().keySet());
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

    @RequestMapping("gototeamOrderManage")
    public ModelAndView gototeamOrderManagement (HttpServletRequest request, ModelAndView modelAndView){
        int idteam = (int)request.getSession().getAttribute("idteam");
        OrderService orderService = new OrderService();
        List<Order> orderList = orderService.getOrdersByidteam(idteam);
        modelAndView = new ModelAndView("order/ordermanage");
        modelAndView.addObject("orders",orderList);
        return modelAndView;
    }
    @RequestMapping("teamOrderManagement")
    public String teamOrderManagement(HttpServletRequest request){

        return "/order/ordermanage";
    }

}

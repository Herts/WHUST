package whustore.cont;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import whustore.dao.OrderDao;
import whustore.model.Order;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {

    @RequestMapping("addorder")
    public String addOrder (HttpServletRequest request){
        Order order = (Order)request.getSession().getAttribute("order");
        OrderDao od = new OrderDao();
//        Product productA = new Product();
//        Product productB = new Product();
//        productA.setId(2);
//        HashMap<Product,Integer> productIntegerMap = new HashMap<Product,Integer>();
//        productIntegerMap.put(productA,10);
//        productB.setId(3);
//        productIntegerMap.put(productB,10);
//        order.setItems(productIntegerMap);
//        order.setIdOrder(100);
//        order.setIduser(16);
        boolean isAdd = false;
        isAdd = od.addOrder(order);
        if(isAdd){
            return "home";
        }
        else{
            return "";
        }

    }

}

package whustore.cont;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import whustore.dao.CartDao;
import whustore.dao.CustomerDao;
import whustore.dao.OrderDao;
import whustore.dao.UserDao;
import whustore.model.*;
import whustore.service.ProductService;

import java.util.List;

@Controller
public class TestController {

    @RequestMapping("Test")
    public String test()
    {

        return "HangingAroundPage";
    }
    @RequestMapping("TestOrder")
    public String testorder(){

        OrderDao dao = new OrderDao();
        List<Order> orderlist = dao.getOrderlist(1);
        return null;
    }
    @RequestMapping("Picture")
    public String pictureTest(){
        ProductService ps = new ProductService();
        Product product = new Product();
        Picture picture = new Picture();
        product.setId(5);
        picture.setIdpicture(1);
        ps.addProductPicture(product,picture);
        return "homepage";
    }

}

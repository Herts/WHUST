package whustore.cont;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import whustore.dao.CartDao;
import whustore.dao.CustomerDao;
import whustore.dao.OrderDao;
import whustore.dao.UserDao;
import whustore.model.*;
import whustore.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

}

package whustore.cont;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import whustore.data.ProductData;
import whustore.model.*;
import whustore.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TestController {

    @RequestMapping("test")
    public String ttt(HttpServletRequest request) {

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        return "shop";
    }
}

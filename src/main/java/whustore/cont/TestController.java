package whustore.cont;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import whustore.dao.CartDao;
import whustore.model.Cart;

@Controller
public class TestController {

    @RequestMapping("Test")
    public String test()
    {
        CartDao cartDao = new CartDao();
        Cart cart = cartDao.getUserCart(1);
        return null;
    }
}

package whustore.cont;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import whustore.model.Cart;
import whustore.model.User;
import whustore.service.CartService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CartController {
    private CartService service = new CartService();

    @RequestMapping("cart/add")
    public void addProductToCart(HttpServletRequest request,
                                 @RequestParam("productID") int productID) {
        User user = (User) request.getSession().getAttribute("user");
        int num = 1;
        if (request.getParameter("number") != null)
            num = Integer.parseInt(request.getParameter("number"));
        service.addProductToCart(user.getUserid(), productID, num);
        Cart userCart = (Cart) request.getSession().getAttribute("cart");
        userCart.addItem(productID,num);
        request.getSession().setAttribute("cart",userCart);
    }

    @RequestMapping("cart/get")
    public void getUserCart() {
        service.getUserCart(16);
    }
}

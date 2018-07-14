package whustore.cont;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

    /**
     * 添加商品到购物车
     * 没有number参数默认为加一个
     * <p>
     * (@param number 要添加的数量）
     *
     * @param request   HttpServletRequest
     * @param productID 要添加的产品艾迪
     */
    @RequestMapping("cart/add")
    public void addProductToCart(HttpServletRequest request,
                                 @RequestParam("productID") int productID) {
        User user = (User) request.getSession().getAttribute("user");
        int num = 1;
        if (request.getParameter("number") != null)
            num = Integer.parseInt(request.getParameter("number"));
        service.addProductToCart(user.getUserid(), productID, num);
        Cart userCart = (Cart) request.getSession().getAttribute("cart");
        userCart.addItem(productID, num);
        request.getSession().setAttribute("cart", userCart);
    }

    @RequestMapping("cart/sub")
    public void subProductIncart(HttpServletRequest request,
                                 @RequestParam("productID") int productID) {
        User user = (User) request.getSession().getAttribute("user");
        int num = 1;
        service.subProductInCart(productID,user.getUserid());
        Cart userCart = (Cart) request.getSession().getAttribute("cart");
        userCart.sub(productID);
    }

    /**
     * 从购物车移除商品
     *
     * @param request   HttpServletRequest
     * @param productID 要移除的产品艾迪
     */
    @RequestMapping("cart/remove")
    public void getUserCart(HttpServletRequest request,
                            @RequestParam("productID") int productID) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //系统操作
        service.removeProduct(productID, cart.getCartID());
        //会话操作
        Cart userCart = (Cart) request.getSession().getAttribute("cart");
        userCart.remove(productID);
        request.getSession().setAttribute("cart", userCart);
    }

    /**
     * 展示我的购物车
     *
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping("cart/myCart")
    public ModelAndView showMyCart(HttpServletRequest request,
                                   ModelMap modelMap) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        modelMap.addAttribute("cart", cart);
        modelMap.addAttribute("items", cart.getItems().keySet());
        return new ModelAndView("cart/cart");
    }
}

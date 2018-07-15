package whustore.cont;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import whustore.model.Cart;
import whustore.model.Product;
import whustore.model.User;
import whustore.service.CartService;
import whustore.service.ProductService;
import whustore.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TeamController {

    @RequestMapping("gotoTeamLogin")
    public ModelAndView logPageTwo() {
        return new ModelAndView("user/teamLogin", "command", new User());
    }

    @RequestMapping("teamLogin")
    public ModelAndView teamLoginChecker(User user, ModelMap modelMap, HttpServletRequest request){
        UserService us = new UserService();
        User userInDB = us.loginCheck(user);
        int idteam = Integer.parseInt(request.getParameter("idteam"));
        if (userInDB == null) {
            modelMap.addAttribute("message", "账号或密码错误");
            return new ModelAndView("user/teamLogin", "command", new User());
        }
        if(!us.checkTeamid(userInDB.getUserid(),idteam)){
            modelMap.addAttribute("message", "");
            return new ModelAndView("user/teamLogin", "command", new User());
        }
        //向对话添加用户对象
        request.getSession().setAttribute("user", userInDB);
        CartService cs = new CartService();
        Cart userCart = cs.getUserCart(userInDB.getUserid());
        request.getSession().setAttribute("cart", userCart);
        request.getSession().setAttribute("idteam",idteam);
        return new ModelAndView("homepage");
    }
    @RequestMapping("gototeamAccount")
    public String gototeamAccount(){
        return "user/teamAccount";
    }
    @RequestMapping("tp")
    public String tp (){
        ProductService ps = new ProductService();
        List<Product> list = new ArrayList<Product>();
        list =ps.getProductsByidteam(1);
        return "homepage";
    }
    @RequestMapping("teamProductList")
    public ModelAndView gototeamProductList(ModelMap modelMap, HttpServletRequest request){
        int idteam = (int)request.getSession().getAttribute("idteam");
        ProductService ps = new ProductService();
        List<Product> list = ps.getProductsByidteam(idteam);
        //modelMap.addAttribute("productList",list);
        ModelAndView modelAndView = new ModelAndView("teamProductList");
        modelAndView.addObject("productList",list);
        return modelAndView;
    }

}

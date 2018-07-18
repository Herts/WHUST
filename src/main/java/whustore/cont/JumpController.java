package whustore.cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import whustore.dao.DBConnector;
import whustore.data.ProductData;
import whustore.model.Product;
import whustore.model.Recommend;
import whustore.model.User;
import whustore.service.RecommendService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class JumpController {

    /**
     * 跳转到主页面
     *
     * @return
     */
    @RequestMapping("home")
    public String toHomePage() {
        return "homepage";
    }

    /**
     * 随便逛逛页面跳转
     *
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping("HangingAround")
    public ModelAndView hangAround(HttpServletRequest request, ModelMap modelMap) {
        List<Product> products = ProductData.getProductList();
        if (products.size() > 22)
            products = products.subList(0, 22);
        modelMap.addAttribute("products",products);
        return new ModelAndView("HangingAroundPage");
    }

    @RequestMapping("/gotocustomerLogin")
    public ModelAndView gotoCustomerLogin() {
        return new ModelAndView("user/login", "command", new User());
    }

    @RequestMapping("/gototeamLogin")
    public ModelAndView gotoTeamLogin() {
        return new ModelAndView("user/teamLogin", "command", new User());
    }
    @RequestMapping("/administrator")
    public String administrator(){return "/Management/Super";}
}

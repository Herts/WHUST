package whustore.cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import whustore.service.ShopService;

@Controller
public class ShopController {
    @Autowired
    ShopService service;

    @RequestMapping("shop")
    public ModelAndView getShopPage(@RequestParam("page") String page, ModelMap modelMap) {

        service = new ShopService();
        

        return new ModelAndView("shop");
    }
}

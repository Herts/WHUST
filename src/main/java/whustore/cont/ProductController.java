package whustore.cont;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
    /**
     * 获取单个商品页面
     *
     * @param productID
     * @param modelMap
     * @return
     */
    @RequestMapping("product")
    public ModelAndView getSingleProduct(@RequestParam("productID") int productID, ModelMap modelMap) {
        modelMap.addAttribute("peoductID", productID);
        return new ModelAndView("singleProduct");
    }
}

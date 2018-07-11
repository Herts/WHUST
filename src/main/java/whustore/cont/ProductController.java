package whustore.cont;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import whustore.data.ProductData;
import whustore.model.Product;

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
        Product product = ProductData.getProductByID(productID);
        modelMap.addAttribute("product",product);
        return new ModelAndView("singleProduct");
    }
}

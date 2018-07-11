package whustore.cont;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import whustore.data.ProductData;
import whustore.model.Product;

import java.util.List;

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
        List<Product> alsoLikes = ProductData.getProductList().subList(2,8);
        modelMap.addAttribute("alsoLikes",alsoLikes);
        return new ModelAndView("singleProduct");
    }
}

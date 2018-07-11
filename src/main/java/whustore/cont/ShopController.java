package whustore.cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import whustore.model.Product;
import whustore.model.Recommend;
import whustore.service.RecommendService;
import whustore.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Controller
public class ShopController {
    @Autowired
    RecommendService service;

    @RequestMapping("shop")
    public ModelAndView getShopByPage(@RequestParam("page") String page, ModelMap modelMap,
                                      HttpServletRequest request) {

        service = new RecommendService();
        Recommend recommend = service.getRecommendation();
        List<Product> products= recommend.getRecommendList();
        products = products.subList(0,9);
        request.getSession().setAttribute("productList", products);
        request.getSession().setAttribute("categories", getCategoryList());
        return new ModelAndView("shop");
    }

    /**
     * 获取单个分类全部商品
     *
     * @param cate
     * @param modelMap
     * @return
     */
    @RequestMapping("shopByCate")
    public ModelAndView getShopByCategory(@RequestParam("category") String cate, ModelMap modelMap) {
        String[] cates = {cate};
        return getShopByCategoryList(cates, modelMap);
    }

    /**
     * 获取许多分类的全部商品
     *
     * @param cates
     * @param modelMap
     * @return
     */
    @RequestMapping("shopByCates")
    public ModelAndView getShopByCategoryList(@RequestParam("categories") String[] cates, ModelMap modelMap) {
        return new ModelAndView(("shop"));
    }

    private List<String> getCategoryList() {
        List<String> categoryList = new ArrayList<String>();
        categoryList.add("上衣");
        categoryList.add("下装");
        categoryList.add("配件");
        categoryList.add("虚拟");
        categoryList.add("纸品");
        categoryList.add("宿舍");
        categoryList.add("回忆");
        categoryList.add("纪念");
        categoryList.add("独创");
        categoryList.add("其他");
        return categoryList;
    }
}

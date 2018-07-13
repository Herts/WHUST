package whustore.cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import whustore.data.ProductData;
import whustore.model.Product;
import whustore.model.ProductComparatorName;
import whustore.model.ProductComparatorPrice;
import whustore.model.Recommend;
import whustore.service.RecommendService;
import whustore.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class ShopController {
    @Autowired
    ShopService service;

    /**
     * 获取某一页面的商品
     *
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping("shop")
    public ModelAndView getShopByPage(ModelMap modelMap,
                                      HttpServletRequest request) {
        int page = 1;
        if (request.getParameter("page") == null) {
            //清除所有的筛选操作，重新返回所有商品列表
            List<Product> list = ProductData.getProductList();
            request.getSession().setAttribute("userFilterProductList", list);
            request.getSession().setAttribute("userFilterCates", getCategoryList());
            return returnNoneFilterPage(modelMap);
        }
        //是以及筛选过的结果的换页操作
        page = Integer.parseInt(request.getParameter("page"));
        //获取当前的筛选后的商品的数组
        List<Product> products = (List<Product>) request.getSession().getAttribute("userFilterProductList");
        List<Product> pageList = new ArrayList<Product>();
        int productSize = products.size();
        if ((page - 1) * 9 < products.size())
            pageList = products.subList(
                    //当前页第一个商品索引
                    9 * (page - 1),
                    //当前页最后一个商品索引
                    (page * 9) < products.size() ? (page * 9) : products.size());
        //添加页商品list
        modelMap.addAttribute("productList", pageList);
        //添加用于展示的分类信息
        modelMap.addAttribute("categories", getCategoryList());
        //把筛选信息存入会话
        if (request.getSession().getAttribute("userFilterCates") != null)
            modelMap.addAttribute("userFilter",
                    request.getSession().getAttribute("userFilterCates"));
        modelMap.addAttribute("allProductsSize", productSize);
        modelMap.addAttribute("page", page);
        return new ModelAndView("shop");
    }

    /**
     * 获取若干分类的全部商品
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("shop/byCates")
    public ModelAndView getShopByCategoryList(HttpServletRequest request,
                                              ModelMap modelMap) {
        String[] cates;
        if (request.getParameterValues("categories") != null)
            cates = request.getParameterValues("categories");
        else
            cates = new String[]{};
        //转换成list
        List<String> userFilterCates = new ArrayList<String>(Arrays.asList(cates));

        List<Product> products = (List<Product>) request.getSession().getAttribute("userFilterProductList");
        List<Product> list = ProductData.getProductByCates(userFilterCates, products);
        int allResultSize = list.size();
        if (list.size() > 9)
            list = list.subList(0, 9);
        modelMap.addAttribute("productList", list);
        modelMap.addAttribute("categories", getCategoryList());
        modelMap.addAttribute("userFilter", userFilterCates);
        modelMap.addAttribute("allProductsSize", allResultSize);
        modelMap.addAttribute("page", 1);
        request.getSession().setAttribute("userFilterProductList", list);
        request.getSession().setAttribute("userFilterCates", userFilterCates);
        return new ModelAndView(("shop"));
    }

    @RequestMapping("shop/byOrder")
    public ModelAndView shopByOrder(HttpServletRequest request,
                                    ModelMap modelMap) {
        String orderType = request.getParameter("orderType");
        List<Product> list;
        if (orderType == "default") {
        } else {
            //获取当前的筛选结果产品数组
            list = (List<Product>) request.getSession().getAttribute("userFilterProductList");
            if (orderType.equals("nameASC")) {
                list.sort(new ProductComparatorName());
            } else if (orderType.equals("nameDES")) {
                list.sort(new ProductComparatorName());
                Collections.reverse(list);
            } else if (orderType.equals("priceASC")) {
                list.sort(new ProductComparatorPrice());
                Collections.reverse(list);
            } else {
                list.sort(new ProductComparatorPrice());
            }
            Object userFilterCates = request.getSession().getAttribute("userFilterCates");
            int allResultSize = list.size();
            List<Product> subLi = new ArrayList<>();
            if (list.size() > 9)
                subLi = list.subList(0, 9);
            modelMap.addAttribute("productList", subLi);
            modelMap.addAttribute("categories", getCategoryList());
            modelMap.addAttribute("userFilter", userFilterCates);
            modelMap.addAttribute("allProductsSize", allResultSize);
            modelMap.addAttribute("page", 1);
            request.getSession().setAttribute("userFilterProductList", list);
        }
        return new ModelAndView("shop");
    }

    @RequestMapping("shop/search")
    public ModelAndView shopBySearch(HttpServletRequest request,
                                     ModelMap modelMap) {
        if (service == null)
            service = new ShopService();
        if (request.getParameter("searching") != null || request.getParameter("searchinfo").toString().length() == 0) {
            request.getSession().setAttribute("userFilterCates", getCategoryList());
            String searching = request.getParameter("searching");
            List<Product> results = service.getProductsBySearch(searching);
            int allResultSize = results.size();
            if (results.size() > 9)
                results = results.subList(0, 9);
            modelMap.addAttribute("productList", results);
            modelMap.addAttribute("categories", getCategoryList());
            modelMap.addAttribute("userFilter", getCategoryList());
            modelMap.addAttribute("allProductsSize", allResultSize);
            modelMap.addAttribute("page", 1);
            request.getSession().setAttribute("userFilterProductList", results);
            request.getSession().setAttribute("userFilterCates", getCategoryList());

            return new ModelAndView("shop");
        } else
            return getShopByPage(modelMap, request);
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

    private ModelAndView returnNoneFilterPage(ModelMap modelMap) {
        int page = 1;
        List<Product> products = ProductData.getProductList();
        int productSize = products.size();
        if ((page - 1) * 9 < products.size())
            products = products.subList(
                    9 * (page - 1),
                    (page * 9) < products.size() ? (page * 9) : products.size());
        modelMap.addAttribute("productList", products);
        modelMap.addAttribute("categories", getCategoryList());
        modelMap.addAttribute("allProductsSize", productSize);
        modelMap.addAttribute("page", page);
        return new ModelAndView("shop");
    }
}

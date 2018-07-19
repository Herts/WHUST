package whustore.cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import whustore.data.ProductData;
import whustore.data.UserRecordData;
import whustore.model.*;
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
     * 可能是没有筛选过的或者 筛选|搜索 过的
     */
    @RequestMapping("shop")
    public ModelAndView getShopByPage(ModelMap modelMap,
                                      HttpServletRequest request) {
        int page = 1;
        if (request.getParameter("page") == null) {
            //清除所有的筛选操作，重新返回所有商品列表
            List<Product> list = ProductData.getProductList();
            //设置初始商品列表和分类
            request.getSession().setAttribute("originList", list);
            request.getSession().setAttribute("userFilterCates", getCategoryList());
            request.getSession().removeAttribute("searchedList");
            request.getSession().removeAttribute("currentFilteredList");
            return returnNoneFilterPage(modelMap);
        }
        //获取当前商品列表
        List<Product> currentFilteredList;
        if (request.getSession().getAttribute("currentFilteredList") != null)
            currentFilteredList = (List<Product>) request.getSession().getAttribute("currentFilteredList");
        else
            currentFilteredList = ProductData.getProductList();
        //是以前筛选过的结果的换页操作
        page = Integer.parseInt(request.getParameter("page"));
        List<Product> pageList = new ArrayList<Product>();
        int productSize = currentFilteredList.size();
        if ((page - 1) * 9 < currentFilteredList.size())
            pageList = currentFilteredList.subList(
                    //当前页第一个商品索引
                    9 * (page - 1),
                    //当前页最后一个商品索引
                    (page * 9) < currentFilteredList.size() ? (page * 9) : currentFilteredList.size());
        //添加页商品list
        modelMap.addAttribute("productList", pageList);
        //添加用于展示的分类信息
        if (request.getSession().getAttribute("userFilterCates") != null) {
            //之前有过筛选，添加之前的筛选列表
            modelMap.addAttribute("categories",
                    request.getSession().getAttribute("userFilterCates"));
        } else {
            //之前没有筛选，获取新的列表
            modelMap.addAttribute("categories", getCategoryList());
        }
        modelMap.addAttribute("allProductsSize", productSize);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("title", "检索列表");
        return new ModelAndView("shop");
    }

    /**
     * 获取若干分类的全部商品
     */
    @RequestMapping("shop/byCates")
    public ModelAndView getShopByCategoryList(HttpServletRequest request,
                                              ModelMap modelMap) {
        String[] cates;
        if (request.getParameterValues("categories") != null) {
            cates = request.getParameterValues("categories");
            //保存分类记录
            if (request.getSession().getAttribute("user") != null) {
                User user = (User) request.getSession().getAttribute("user");
                if (UserRecordData.getUserRecord(user.getUserid()) != null) {
                    UserRecord userRecord = UserRecordData.getUserRecord(user.getUserid());
                    for (String info :
                            cates) {
                        userRecord.addCateFiltered(info);
                    }
                }
            }
        } else
            cates = new String[]{};
        //转换成list
        List<String> userFilterCates = new ArrayList<>(Arrays.asList(cates));
        //获取商品列表
        List<Product> products;
        if (request.getSession().getAttribute("searchedList") != null) {
            //是搜索后继续筛选
            products = (List<Product>) request.getSession().getAttribute("searchedList");
        } else {
            //是新的分类筛选
            products = ProductData.getProductList();
        }

        //获取分类筛选后的商品列表
        List<Product> list = ProductData.getProductByCates(userFilterCates, products);
        int allResultSize = list.size();

        //剪切展示列表
        List<Product> showList;
        if (list.size() > 9)
            showList = list.subList(0, 9);
        else
            showList = list;

        modelMap.addAttribute("productList", showList);
        modelMap.addAttribute("categories", getCategoryList());
        modelMap.addAttribute("userFilter", userFilterCates);
        modelMap.addAttribute("allProductsSize", allResultSize);
        modelMap.addAttribute("page", 1);
        request.getSession().setAttribute("currentFilteredList", list);
        request.getSession().setAttribute("userFilterCates", userFilterCates);
        return new ModelAndView(("shop"));
    }

    @RequestMapping("shop/byOrder")
    public ModelAndView shopByOrder(HttpServletRequest request,
                                    ModelMap modelMap) {
        String orderType = request.getParameter("orderType");
        List<Product> list;
        if (orderType == "default") {
            list = ProductData.getProductList();
        } else {
            //获取当前的筛选结果产品数组
            if (request.getSession().getAttribute("currentFilteredList") != null) {
                list = (List<Product>) request.getSession().getAttribute("currentFilteredList");
            } else {
                list = ProductData.getProductList();
            }
            //根据orderType排序
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
            //之前是否有过分类筛选
            if (request.getSession().getAttribute("userFilterCates") != null) {
                Object userFilterCates = request.getSession().getAttribute("userFilterCates");
                modelMap.addAttribute("userFilter", userFilterCates);
            } else {
                modelMap.addAttribute("userFilter", getCategoryList());
            }
            //有必要的话裁切
            int allResultSize = list.size();
            List<Product> subLi = new ArrayList<>();
            if (list.size() > 9)
                subLi = list.subList(0, 9);
            else
                subLi = list;

            modelMap.addAttribute("productList", subLi);
            modelMap.addAttribute("categories", getCategoryList());
            modelMap.addAttribute("allProductsSize", allResultSize);
            modelMap.addAttribute("page", 1);
        }
        return new ModelAndView("shop");
    }

    @RequestMapping("shop/search")
    public ModelAndView shopBySearch(HttpServletRequest request,
                                     ModelMap modelMap) {
        if (service == null)
            service = new ShopService();
        if (request.getParameter("searching") != null || request.getParameter("searchinfo").toString().length() != 0) {


            //搜索逻辑
            request.getSession().setAttribute("userFilterCates", getCategoryList());
            String searching = request.getParameter("searching");

            //保存搜索记录
            if (request.getSession().getAttribute("user") != null) {
                User user = (User) request.getSession().getAttribute("user");
                if (UserRecordData.getUserRecord(user.getUserid()) != null) {
                    UserRecord userRecord = UserRecordData.getUserRecord(user.getUserid());
                    userRecord.addSearchInfo(searching);
                }
            }

            List<Product> results = service.getProductsBySearch(searching);
            request.getSession().setAttribute("searchedList", results);
            request.getSession().setAttribute("currentFilteredList", results);
            int allResultSize = results.size();
            List<Product> showProducts = new ArrayList<>();
            if (results.size() > 9)
                showProducts = results.subList(0, 9);
            else
                showProducts = results;

            modelMap.addAttribute("productList", results);
            modelMap.addAttribute("categories", getCategoryList());
            modelMap.addAttribute("userFilter", getCategoryList());
            modelMap.addAttribute("allProductsSize", allResultSize);
            modelMap.addAttribute("page", 1);
            modelMap.addAttribute("title", "检索列表");
            request.getSession().setAttribute("userFilterCates", getCategoryList());

            return new ModelAndView("shop");
        } else
            return getShopByPage(modelMap, request);
    }

    public static List<String> getCategoryList() {
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
        modelMap.addAttribute("title", "全部商品");
        return new ModelAndView("shop");
    }
}

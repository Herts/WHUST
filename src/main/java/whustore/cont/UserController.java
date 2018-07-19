package whustore.cont;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import whustore.model.*;
import whustore.service.CartService;
import whustore.service.CustomerService;
import whustore.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 第一个RequestMapping的纪念碑
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("hello")
    public String hello(ModelMap modelMap) {
        modelMap.addAttribute("username", "what");
        return "hello";
    }


    /**
     * 用于登陆检查
     *
     * @param user
     * @param modelMap
     * @return
     */
    //登陆密码检查
    @RequestMapping("login")
    public ModelAndView LogChecker(@ModelAttribute("SpringWeb") User user,
                                   ModelMap modelMap) {
        userService = new UserService();
        User userInDB = userService.loginCheck(user);
        if (userInDB == null) {
            modelMap.addAttribute("message", "账号或密码错误");
            return new ModelAndView("user/login", "command", new User());
        }
        //向对话添加用户对象
        request.getSession().setAttribute("user", userInDB);
        CartService service = new CartService();
        Cart userCart = service.getUserCart(userInDB.getUserid());
        request.getSession().setAttribute("cart", userCart);
        if(userInDB.getUserid()==0){
            request.getSession().setAttribute("super",userInDB.getUserid());
        }
        return new ModelAndView("homepage");
    }

    /**
     * 注销账户
     *
     * @return
     */
    @RequestMapping("logOut")
    public ModelAndView logOut() {
        request.getSession().removeAttribute("user");
        return new ModelAndView("homepage");
    }

    /**
     * 跳转至登陆页面
     *
     * @return
     */
    //登陆页面跳转
    @RequestMapping("log")
    public ModelAndView logPage() {
        return new ModelAndView("user/login", "command", new User());
    }

    /**
     * 我的账户页面
     *
     * @param modelMap
     * @return
     */
    //账户详情跳转
    @RequestMapping("/user/home")
    public ModelAndView toUserHome(ModelMap modelMap) {
        CustomerService cs = new CustomerService();
        Customer cus = cs.getCustomer((User) request.getSession().getAttribute("user"));
        modelMap.addAttribute("customer", cus);
        if (cus != null) {
            if (cus.getSex() != null && cus.getSex().equalsIgnoreCase("f"))
                modelMap.addAttribute("fs", "selected");
            else
                modelMap.addAttribute("ms", "selected");
        }
        return new ModelAndView("user/myAccount", "command", new Customer());
    }


    /**
     * 用户注册
     *
     * @param user
     * @param modelMap
     * @return
     */
    //注册用户
    @RequestMapping("reg")
    public ModelAndView Reg(@ModelAttribute("SpringWeb") User user,
                            ModelMap modelMap) {

        if (!userService.userReg(user)) {
            //注册失败
            modelMap.addAttribute("message", "注册失败");
            modelMap.addAttribute("isReg", "true");
            return new ModelAndView("user/login", "command", new User());
        } else {
            //注册成功
            modelMap.addAttribute("message","注册成功");
            return new ModelAndView("user/login");
        }
    }

    @RequestMapping(path = "/manageUser")
    public ModelAndView manageUser(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.getAllUser());
        return new ModelAndView("user/userlist");
    }

    @RequestMapping(path = "/manageUser/add", method = RequestMethod.POST)
    public String saveUser(User user, BindingResult result,
                           ModelMap model) {

        if (result.hasErrors()) {
            return "user/regisration";
        }
        if (!userService.isUsernameUnique(user.getUsername())) {
//            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
//            result.addError(ssoError);
            return "user/regisration";
        }

        userService.userReg(user);

        model.addAttribute("success", "User " + user.getUserid() + " " + user.getUsername() + " registered successfully");
        //return "success";
        return "user/regisrationsuccess";
    }


    @RequestMapping(path = "/manageUser/add")
    public ModelAndView addUser(ModelMap modelMap) {
        User user = new User();
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("edit", false);
        return new ModelAndView("user/registration");
    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = { "manageUser/edit-user-{userid}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable int userid, ModelMap model) {
        User user = userService.getUserByIduser(userid);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "user/registration";
    }

    @RequestMapping(value = { "manageUser/update-user-{userid}" }, method = RequestMethod.POST)
    public String updateUser(User user, BindingResult result,
                             ModelMap model, @PathVariable int userid) {
        if(result.hasErrors()){
            return "user/registration";
        }
        userService.updateUser(user);
        model.addAttribute("success", "User " + user.getUserid() + " "+ user.getUsername() + " updated successfully");
        return "user/registrationsuccess";

    }

    /**
     * This method will delete an user by it's SSOID value.
     */
    @RequestMapping(value = { "manageUser/delete-user-{userid}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable int userid) {
        userService.deleteUserByIduser(userid);
        return "redirect:/manageUser";
    }



    @RequestMapping("/user/myStory")
    public ModelAndView userStory(HttpServletRequest request,
                                  ModelMap modelMap) {
        User user = (User) request.getSession().getAttribute("user");
        Num orderSize = new Num();
        orderSize.setINT(0);
        Map<Date, Double> totalGrow = new TreeMap<>();
        Map<Date, Integer[]> orderGrow = new TreeMap<>();
        modelMap.addAttribute("totalGrow", totalGrow);
        modelMap.addAttribute("orderGrow", orderGrow);
        //获取购买过的商品和购买数的map
        Map<Product, Integer> productNumMap = userService.getUserProductRecords(user, orderSize, totalGrow, orderGrow);
        Num productNumber = new Num();
        productNumber.setINT(0);
        //根据购买的商品获取购买过的商品种类的map
        Map<String, Integer> cateMap = cateNums(productNumMap, productNumber);
        //获取种类map中数目前4的
        List<String> sorted = sortedCates(cateMap);
        int cateOthers = productNumber.getINT();
        //购买次数最多的单个分类的数量
        int maxSingleCateNums = 0;
        for (String cate :
                sorted) {
            if (cateMap.get(cate) > maxSingleCateNums)
                maxSingleCateNums = cateMap.get(cate);
            cateOthers -= cateMap.get(cate);
        }
        modelMap.addAttribute("maxSingleCateNums", maxSingleCateNums);
        modelMap.addAttribute("otehrCateSize", cateOthers);
        modelMap.addAttribute("favCates", sorted);
        List<Product> favParoduct = getFavProducts(productNumMap);
        modelMap.addAttribute("favProducts", favParoduct);
        modelMap.addAttribute("productNumber", productNumber.getINT());
        modelMap.addAttribute("orderSize", orderSize.getINT());
        //获取总消费额
        int totalMoney = 0;
        List<Integer> teams = new ArrayList<>();
        for (Product product :
                productNumMap.keySet()) {
            totalMoney += product.getPrice() * productNumMap.get(product);
            if (!teams.contains(product.getTeamID()))
                teams.add(product.getTeamID());
        }


        modelMap.addAttribute("productNumMap", productNumMap);
        modelMap.addAttribute("cateNumMap", cateMap);
        modelMap.addAttribute("teamSize", teams.size());
        modelMap.addAttribute("totalMoney", totalMoney);
        return new ModelAndView("userStory");
    }

    /**
     * 获取购买最多的商品
     *
     * @param productNumMap 商品销量map
     * @return 前五的商品
     */
    private List<Product> getFavProducts(Map<Product, Integer> productNumMap) {
        List<Product> favProducts = new ArrayList<>();
        int maxBought = 0;
        for (Product product :
                productNumMap.keySet()) {
            if (productNumMap.get(product) > maxBought)
                maxBought = productNumMap.get(product);
        }

        for (int i = maxBought; i > 0 && favProducts.size() < 4; i--) {
            for (Product product :
                    productNumMap.keySet()) {
                if (productNumMap.get(product) == i)
                    if (favProducts.size() < 4)
                        favProducts.add(product);
                    else break;
            }
        }
        return favProducts;
    }

    /**
     * 获取用户买过的商品的分类分布情况
     *
     * @param historyProductRecords 用户购买过的商品和商品的数量
     * @return 用户买过的商品的分类分布情况
     */
    private Map<String, Integer> cateNums(Map<Product, Integer> historyProductRecords, Num productNumber) {
        Map<String, Integer> result = new HashMap<>();
        //遍历产品
        int originNum = 0;
        int productNum = 0;
        for (Product product :
                historyProductRecords.keySet()) {
            productNumber.setINT(historyProductRecords.get(product) + productNumber.getINT());
            //没有分类跳过该商品
            if (product.getTypes() == null) continue;
            //遍历单个产品的分类
            for (String currentProductType :
                    product.getTypes()) {
                if (currentProductType == null) continue;
                productNum = historyProductRecords.get(product);
                if (result.get(currentProductType) != null) {
                    //结果map里已经包含当前分类
                    originNum = result.get(currentProductType);
                    result.remove(currentProductType);
                    result.put(currentProductType, originNum + productNum);
                } else {
                    //结果map不包含当前分类
                    result.put(currentProductType, productNum);
                }
            }
        }
        return result;
    }


    /**
     * 获取按商品数量排序的购买过的分类的列表
     *
     * @param cateMap 分类购买过商品数量map
     * @return 排序过的分类list
     */
    private List<String> sortedCates(Map<String, Integer> cateMap) {
        int maxNum = 0;
        List<String> sortedCates = new ArrayList<>();
        for (String string :
                cateMap.keySet()) {
            if (cateMap.get(string) > maxNum)
                maxNum = cateMap.get(string);
        }

        for (int i = maxNum; i > 0 && sortedCates.size() < 5; i--) {
            for (String string :
                    cateMap.keySet()) {
                if (cateMap.get(string) == i) {
                    if (sortedCates.size() < 5) {
                        sortedCates.add(string);
                    } else {
                        break;
                    }
                }
            }
        }
        return sortedCates;
    }
}

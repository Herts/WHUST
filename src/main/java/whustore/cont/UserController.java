package whustore.cont;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import whustore.model.Customer;
import whustore.model.User;
import whustore.service.CustomerService;
import whustore.service.UserService;

import javax.servlet.http.HttpServletRequest;


@Controller
public class UserController {

    @Autowired
    private UserService service;

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
        service = new UserService();
        User userInDB = service.loginCheck(user);
        if (userInDB == null) {
            modelMap.addAttribute("message", "账号或密码错误");
            return new ModelAndView("user/login", "command", new User());
        }
        //向对话添加用户对象
        request.getSession().setAttribute("user", userInDB);
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
        return new ModelAndView("hello");
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
        service = new UserService();
        if (!service.userReg(user)) {
            //注册失败
            modelMap.addAttribute("message", "注册失败");
            modelMap.addAttribute("isReg", "true");
            return new ModelAndView("user/login", "command", new User());
        } else {
            //注册成功
            modelMap.addAttribute("username", user.getUsername());
            modelMap.addAttribute("password", user.getPassword());
            return new ModelAndView("hello");
        }
    }
}

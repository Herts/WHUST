package whustore.cont;

import jdk.nashorn.internal.objects.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import whustore.dao.UserDao;
import whustore.model.User;
import whustore.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private HttpServletRequest request;

    //测试
    @RequestMapping("hello")
    public String hello(ModelMap modelMap) {
        modelMap.addAttribute("username", "what");
        return "hello";
    }

    //登陆密码检查
    @RequestMapping("login")
    public ModelAndView LogChecker(@ModelAttribute("SpringWeb") User user,
                                   ModelMap modelMap) {
        service = new UserService();
        if (!service.passwordIsCorrect(user)) {
            modelMap.addAttribute("message", "账号或密码错误");
            return new ModelAndView("user/login", "command", new User());
        }
        request.getSession().setAttribute("user", user);
        modelMap.addAttribute("username", ((User)request.getSession().getAttribute("user")).getUsername());
        modelMap.addAttribute("password", user.getPassword());
        return new ModelAndView("homepage");
    }

    //注销
    @RequestMapping("logOut")
    public ModelAndView logOut(){
        request.getSession().removeAttribute("user");
        return new ModelAndView("hello");
    }

    //登陆页面跳转
    @RequestMapping("log")
    public ModelAndView logPage() {
        return new ModelAndView("user/login", "command", new User());
    }

    //账户详情跳转
    @RequestMapping("/user/home")
    public ModelAndView toUserHome() {
        return new ModelAndView("hello");
    }

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

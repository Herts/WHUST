package whustore.cont;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import whustore.dao.UserDao;
import whustore.model.User;
import whustore.service.UserService;


@Controller
public class UserController {
    //测试
    @RequestMapping("hello")
    public String hello(ModelMap modelMap) {
        modelMap.addAttribute("username", "what");
        return "hello";
    }

    //登陆密码检查
    @RequestMapping("loginChecker")
    public ModelAndView LogChecker(@ModelAttribute("SpringWeb") User user,
                                   ModelMap modelMap) {
        UserService userService = new UserService();
        if (!userService.passwordIsCorrect(user)) {
            modelMap.addAttribute("message", "账号或密码错误");
            return new ModelAndView("user/login", "command", new User());
        }
        modelMap.addAttribute("username", user.getUsername());
        modelMap.addAttribute("password", user.getPassword());

        return new ModelAndView("hello");
    }

    //登陆页面调整
    @RequestMapping("log")
    public ModelAndView logPage() {
        return new ModelAndView("user/login", "command", new User());
    }

    //注册用户
    @RequestMapping("reg")
    public ModelAndView Reg(@ModelAttribute("SpringWeb") User user,
                            ModelMap modelMap) {
        UserService us = new UserService();
        if (!us.userReg(user)) {
            //注册失败
            modelMap.addAttribute("message", "注册失败");
            modelMap.addAttribute("isReg","true");
            return new ModelAndView("user/login", "command", new User());
        } else {
            //注册成功
            modelMap.addAttribute("username", user.getUsername());
            modelMap.addAttribute("password", user.getPassword());
            return new ModelAndView("hello");
        }
    }
}

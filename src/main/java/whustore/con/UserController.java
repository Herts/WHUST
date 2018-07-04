package whustore.con;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import whustore.dao.UserDao;
import whustore.model.User;


@Controller
public class UserController {

    @RequestMapping("hello")
    public String hello(ModelMap modelMap)
    {
        modelMap.addAttribute("username", "what");
        return "hello";
    }


    @RequestMapping("loginChecker")
    public String LogPrint(@ModelAttribute("SpringWeb")User user,
                           ModelMap modelMap) {
        modelMap.addAttribute("username", user.getUsername());
        modelMap.addAttribute("password", user.getPassword());
        UserDao userDao = new UserDao();
        userDao.passwordIsCorrect(user);

        return "hello";
    }

    @RequestMapping("log")
    public ModelAndView logPage() {
        return new ModelAndView("user/login","command", new User());
    }

}

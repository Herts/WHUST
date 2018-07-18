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
import whustore.model.Cart;
import whustore.model.Customer;
import whustore.model.User;
import whustore.service.CartService;
import whustore.service.CustomerService;
import whustore.service.UserService;

import javax.servlet.http.HttpServletRequest;


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
            return new ModelAndView("reg");
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


}

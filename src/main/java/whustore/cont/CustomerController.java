package whustore.cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import whustore.model.Customer;
import whustore.model.User;
import whustore.service.CustomerService;
import whustore.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomerController {
    @Autowired
    private CustomerController service;

    @Autowired
    private HttpServletRequest request;


    /**
     * 更改customer的信息
     * @param cus
     * @param modelMap
     * @return
     */
    @RequestMapping("/user/changeInfo")
    public String customerModify(@ModelAttribute("SpringWeb") Customer cus,
                                 ModelMap modelMap) {
        CustomerService cs = new CustomerService();
        User user = (User) request.getSession().getAttribute("user");
        cs.modifyCustomer(cus, user);
        return "redirect:/user/home";
    }
}

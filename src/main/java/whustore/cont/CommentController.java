package whustore.cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import whustore.data.ProductData;
import whustore.model.Comment;
import whustore.model.Product;
import whustore.model.User;
import whustore.service.CommentService;
import whustore.service.ProductService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService = new CommentService();

    @RequestMapping(path = "/comments/add")
    public String setComment(HttpServletRequest request, @RequestParam int idproduct, @RequestParam int level,
                           @RequestParam String title, @RequestParam String content, @RequestParam int clevel){
        boolean isCommented  = (clevel != 0);
        User user = (User) request.getSession().getAttribute("user");
        commentService.setComment(idproduct, user.getUserid(), level, title, content, isCommented);
        return "redirect:/order/myOrders";
    }

    @RequestMapping(path = "/comments/comment")
    public ModelAndView toSetComment(HttpServletRequest request, @RequestParam int idproduct,
                                     ModelMap modelMap){
        Product product = ProductData.getProductByID(idproduct);
        User user = (User) request.getSession().getAttribute("user");
        Comment comment = commentService.getCommentByIduserAndIdproduct(user.getUserid(), idproduct);
        modelMap.addAttribute("product", product);
        modelMap.addAttribute("comment", comment);
        return new ModelAndView("/user/comment");
    }
}

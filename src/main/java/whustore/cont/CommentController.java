package whustore.cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import whustore.model.User;
import whustore.service.CommentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService = new CommentService();
    @RequestMapping(path = "/comments/add")
    public void setComment(HttpServletRequest request, @RequestParam int idproduct, @RequestParam int level,
                           @RequestParam String title, @RequestParam String content){
        User user = (User) request.getSession().getAttribute("user");
        commentService.setComment(idproduct, user.getUserid(), level, title, content);

    }
}

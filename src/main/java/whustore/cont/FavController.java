package whustore.cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import whustore.model.User;
import whustore.service.FavService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FavController {
    @Autowired
    private FavService favService= new FavService();

    @RequestMapping(path = "/fav")
    public void changeFavState(HttpServletRequest request, @RequestParam int idproduct){
        User user = (User) request.getSession().getAttribute("user");
        favService.changeState(user.getUserid(), idproduct);
    }
}


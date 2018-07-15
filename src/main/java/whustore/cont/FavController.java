package whustore.cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import whustore.data.ProductData;
import whustore.model.Product;
import whustore.model.User;
import whustore.service.FavService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FavController {
    @Autowired
    private FavService favService = new FavService();

    @RequestMapping(path = "/fav/add")
    public void changeFavState(HttpServletRequest request, @RequestParam("productID") int idproduct) {
        User user = (User) request.getSession().getAttribute("user");
        favService.changeState(user.getUserid(), idproduct);
    }

    @RequestMapping("/fav/my")
    public ModelAndView showMyFavs(HttpServletRequest request,
                                   ModelMap modelMap) {
        User user = (User) request.getSession().getAttribute("user");
        List<Integer> productIds = favService.getUserFavList(user.getUserid());
        List<Product> products = new ArrayList<>();
        if (productIds.size() > 0)
            for (Integer integer :
                    productIds) {
                products.add(ProductData.getProductByID(integer));
            }
        modelMap.addAttribute("products", products);
        return new ModelAndView("myFavs");
    }
}


package whustore.cont;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import whustore.dao.*;
import whustore.model.*;
import whustore.service.ProductService;

import java.util.List;
@Controller
public class TestController {

    @RequestMapping("Picture")
    public String pictureTest(){
        ProductService ps = new ProductService();
        Product product = new Product();
        Picture picture = new Picture();
        product.setId(5);
        picture.setIdpicture(1);
        ps.addProductPicture(product,picture);
        return "homepage";
    }
    @RequestMapping("product")
    public String productTest(){
        ProductService ps = new ProductService();
        List<Product> list1 = ps.getAllProducts();
        List<Product> list2 = ps.getProductsByType("服饰");
        List<Product> list3 = ps.getProductsByQuery("保研");
        List<Product> list4 = ps.getRandomProducts(10);
        return "homepage";
    }

}

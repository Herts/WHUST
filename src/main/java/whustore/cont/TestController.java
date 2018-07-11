package whustore.cont;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import whustore.data.ProductData;
import whustore.model.*;
import whustore.service.ProductService;

import java.util.List;

@Controller
public class TestController {

    /**
     * 用于测试功能
     *
     * @return
     */
    @RequestMapping("Picture")
    public String pictureTest() {
        ProductService ps = new ProductService();
        Product product = new Product();
        Picture picture = new Picture();
        product.setId(5);
        picture.setIdpicture(1);
        ps.addProductPicture(product, picture);
        return "homepage";
    }

    @RequestMapping("test")
    public String ttt() {
        List<Product> list = ProductData.getProductList();
        return "shop";
    }
}

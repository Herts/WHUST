package whustore.cont;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import whustore.model.Product;
import whustore.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class ProductController {
    @RequestMapping("testaddproduct")
    public String addproduct(HttpServletRequest request, String [] type, @RequestParam MultipartFile pic) {
        ProductService ps = new ProductService();
        Product product = new Product();
        String pname = request.getParameter("productName");
        product.setProductName(pname);
        product.setProIntro(request.getParameter("proIntro"));
        product.setQuantity(Integer.valueOf(request.getParameter("quantity")));
        product.setTeamID(Integer.valueOf(request.getParameter("teamID")));
        try {
            if (!pic.isEmpty()) {
                String basesqlpath = "img/product/";
                String sqlpath = null;
                String loadpath = System.getProperty("rootpath")+"img\\product\\";
                String originalFileName = pic.getOriginalFilename();
                String newFileName = UUID.randomUUID() +originalFileName.substring(originalFileName.lastIndexOf("."));
                loadpath = loadpath + newFileName;
                File newFile = new File(loadpath);
                pic.transferTo(newFile);
                sqlpath = basesqlpath + newFileName;
                product.picPathAppend(sqlpath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ps.addProduct(product);
        return "addproduct";
    }
}

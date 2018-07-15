package whustore.cont;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import whustore.data.ProductData;
import whustore.model.Product;
import whustore.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class ProductController {
    /**
     * 获取单个商品页面
     *
     * @param productID
     * @param modelMap
     * @return
     */
    @RequestMapping("product")
    public ModelAndView getSingleProduct(@RequestParam("productID") int productID, ModelMap modelMap) {
        Product product = ProductData.getProductByID(productID);
        modelMap.addAttribute("product",product);
        List<Product> alsoLikes = ProductData.getProductList().subList(2,8);
        modelMap.addAttribute("alsoLikes",alsoLikes);
        return new ModelAndView("singleProduct");
    }
    @RequestMapping("gotoAddproduct")
    public String gotoAddproduct(){
        return "addproduct";
    }
    @RequestMapping("addproducts")
    public String addproduct(HttpServletRequest request, String[] type, @RequestParam MultipartFile[] pics) {
        ProductService ps = new ProductService();
        Product product = new Product();
        String pname = request.getParameter("productName");
        product.setProductName(pname);
        product.setProIntro(request.getParameter("proIntro"));
        product.setQuantity(Integer.valueOf(request.getParameter("quantity")));
        product.setTeamID(Integer.valueOf(request.getParameter("teamID")));
        try {
            if (pics!=null && pics.length >0) {
                for (MultipartFile mpf: pics
                        ) {
                    String basesqlpath = "img/product/";
                    String sqlpath = null;
                    String loadpath = System.getProperty("rootpath") + "img\\product\\";
                    String originalFileName = mpf.getOriginalFilename();
                    String newFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
                    loadpath = loadpath + newFileName;
                    File newFile = new File(loadpath);
                    mpf.transferTo(newFile);
                    sqlpath = basesqlpath + newFileName;
                    product.picPathAppend(sqlpath);
                }
            }
            if(type!=null){
                for (String t: type
                        ) {
                    product.typeAppend(t);
                }
            }
            ps.addProduct(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "addproduct";
    }
}

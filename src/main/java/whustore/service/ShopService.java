package whustore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whustore.dao.ProductDao;
import whustore.model.Product;

import java.util.List;

@Service
public class ShopService {
    ProductDao productDao;

    public List<Product> getAllProdut()
    {
        productDao = new ProductDao();
        return productDao.getAllProducts();
    }
}

package whustore.service;

import org.springframework.stereotype.Service;
import whustore.dao.ProductDao;
import whustore.model.*;

@Service
public class ProductService {
    public Product getProduct(int productID){
        ProductDao pd = new ProductDao();
        return pd.getProduct(productID);
    }

    public boolean addProduct (Product p){
        ProductDao pd = new ProductDao();
        return pd.addProduct(p);
    }

}

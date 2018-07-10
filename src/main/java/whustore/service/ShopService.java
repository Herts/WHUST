package whustore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whustore.dao.ProductDao;

@Service
public class ShopService {
    ProductDao productDao;
}

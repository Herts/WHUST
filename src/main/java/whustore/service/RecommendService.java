package whustore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whustore.dao.RecommendDao;
import whustore.data.ProductData;
import whustore.data.UserRecordData;
import whustore.model.Product;
import whustore.model.Recommend;
import whustore.model.UserRecord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class RecommendService {
    @Autowired
    RecommendDao dao;

    /**
     * 获取推荐
     *
     * @return
     */
    public Recommend getRecommendation() {
        dao = new RecommendDao();
        return dao.getRecommendation();
    }

    public List<Product> getUsersRecommend(int userId) {
        UserRecord userRecord = UserRecordData.getUserRecord(userId);
        if (userRecord == null)
            return new ArrayList<>();
        List<Product> recommendProducts = new ArrayList<>();
        //根据分类检索记录推荐
        List<String> cate = new ArrayList<>(userRecord.getFiltedCates().keySet());
        List<Product> cateProducts = ProductData.getProductByCates(cate, ProductData.getProductList());
        if (cateProducts.size() < 7)
            recommendProducts.addAll(cateProducts);
        else
            recommendProducts.addAll(cateProducts.subList(0, 7));

        //根据搜索记录推荐
        StringBuilder recordSearch = new StringBuilder();
            for (String info :
                    userRecord.getSearchRecord()) {
                recordSearch.append(info);
            }
        ShopService shopService = new ShopService();
        List<Product> searchProducts = shopService.getProductsBySearch(recordSearch.toString());
        if (cateProducts.size() < 7)
            recommendProducts.addAll(searchProducts);
        else
            recommendProducts.addAll(searchProducts.subList(0, 7));

        //根据收藏获取推荐
        String favProductNames = userRecord.getFavProductNames();
        List<Product> favProducts =  shopService.getProductsBySearch(favProductNames);

        if (cateProducts.size() < 8)
            recommendProducts.addAll(favProducts);
        else
            recommendProducts.addAll(favProducts.subList(0, 8));

        //补足
        if (recommendProducts.size() < 22){
            int need = 22 - recommendProducts.size();
            recommendProducts.addAll(ProductData.getProductList().subList(0,need));
        }

        return recommendProducts;
    }


}

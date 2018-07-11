package whustore.data;

import whustore.dao.ProductDao;
import whustore.model.Product;

import java.util.ArrayList;
import java.util.List;

public class productData {
    private static List<Product> productList = null;
    private static ProductDao dao = new ProductDao();

    /**
     * 获取所有商品的表
     *
     * @return 所有商品列表
     */
    public static List<Product> getProductList() {
        if (productList == null)
            productList = dao.getAllProduct();
        return productList;
    }

    /**
     * 根据商品id移除商品
     *
     * @param id 要移除的商品的id
     * @return 是否成功
     */
    public static boolean removeProduct(int id) {
        for (Product product :
                productList) {
            if (product != null && product.getId() == id)
                return productList.remove(product);
        }
        return false;
    }

    /**
     * 根据商品对象移除商品
     *
     * @param theProduct 要移除的商品
     * @return 是否移除成功
     */
    public static boolean removeProduct(Product theProduct) {
        return productList.remove(theProduct);
    }

    /**
     * 根据商品id获取商品
     *
     * @param id 要获取的商品的id
     * @return 商品
     */
    public static Product getProductByID(int id) {
        for (Product product :
                productList) {
            if (product != null && product.getId() == id)
                return product;
        }
        return null;
    }

    /**
     * 在没有相同id的商品的情况下插入新商品
     *
     * @param newProduct 要插入的新商品
     * @return 是否插入成功
     */
    public static boolean addProduct(Product newProduct) {
        if (getProductByID(newProduct.getId()) == null) {
            productList.add(newProduct);
            return true;
        } else
            return false;
    }
}

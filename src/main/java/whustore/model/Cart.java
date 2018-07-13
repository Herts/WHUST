package whustore.model;

import whustore.data.ProductData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private int cartID;
    private int userID;
    //产品 库存  对应的MAP
    private Map<Product, Integer> items;

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


    public Map<Product, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Product, Integer> itemsAmount) {
        this.items = itemsAmount;
    }

    public void addItem(int productID, int amount) {
        for (Product product :
                items.keySet()) {
            if (product != null && product.getId() == productID) {
                int orignAmount = items.get(product);
                int newAmount = orignAmount + amount;
                items.remove(product);
                items.put(product, newAmount);
                return;
            }
        }
        Product product = ProductData.getProductByID(productID);
        if (product != null)
            items.put(product, amount);
    }

    /**
     * 获取总金额
     *
     * @return 购物车总金额
     */
    public double getTotal() {
        double total = 0;
        for (Product product :
                items.keySet()) {
            if (product != null)
                total += product.getPrice() * items.get(product);
        }
        return total;
    }

    /**
     * 移除商品
     *
     * @param productID 商品艾迪
     */
    public void remove(int productID) {

        for (Product product :
                items.keySet()) {
            if (productID == product.getId()) {
                items.remove(product);
                break;
            }
        }
    }
}

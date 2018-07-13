package whustore.model;

import whustore.data.ProductData;

import java.util.List;
import java.util.Map;

public class Cart {
    private int cartID;
    private int userID;
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
            if (product.getId() == productID) {
                int orignAmount = items.get(product);
                int newAmount = orignAmount + amount;
                items.remove(product);
                items.put(product, newAmount);
                return;
            }
        }
        Product product =  ProductData.getProductByID(productID);
        items.put(product,amount);
    }
}

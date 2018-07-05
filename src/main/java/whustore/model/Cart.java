package whustore.model;

import java.util.List;
import java.util.Map;

public class Cart {
    private int cartID;
    private int userID;
    private List<Map<Product, Integer>> items;

    public List<Map<Product, Integer>> getItems() {
        return items;
    }

    public void setItems(List<Map<Product, Integer>> items) {
        this.items = items;
    }

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
}

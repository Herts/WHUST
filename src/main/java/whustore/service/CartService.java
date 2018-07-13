package whustore.service;

import whustore.dao.CartDao;
import whustore.model.Cart;

public class CartService {
    private CartDao dao = new CartDao();

    public void addProductToCart(int userID, int productID,int number)
    {
        dao.addProductToCart(userID,productID,number);
    }

    public Cart getUserCart(int userID)
    {
         Cart cart = dao.getUserCart(userID);
         return cart;
    }
}

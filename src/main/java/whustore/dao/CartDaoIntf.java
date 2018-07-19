package whustore.dao;

import whustore.model.Cart;

public interface CartDaoIntf {
    Cart getUserCart(int userID);

    void addProductToCart(int userID, int productID, int num);

    boolean deleteCart(int idcart);

    boolean deleteCartitem(int idcart);

    boolean subProductInCart(int productID, int userID);

    boolean removeProduct(int productID, int cartID);

}

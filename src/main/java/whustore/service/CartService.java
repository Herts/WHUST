package whustore.service;

import org.springframework.stereotype.Service;
import whustore.dao.CartDao;
import whustore.model.Cart;
@Service
public class CartService {
    private CartDao dao = new CartDao();

    public void addProductToCart(int userID, int productID, int number) {
        dao.addProductToCart(userID, productID, number);
    }

    public Cart getUserCart(int userID) {
        Cart cart = dao.getUserCart(userID);
        return cart;
    }

    public void subProductInCart(int productID, int userID)
    {
        dao.subProductInCart(productID,userID);
    }

    public void removeProduct(int productID, int cartID) {
        dao.removeProduct(productID, cartID);
    }

    public boolean deleteCartByIdCart(int idcart){
        return dao.deleteCart(idcart);
    }
}

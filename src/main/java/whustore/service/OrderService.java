package whustore.service;

import org.springframework.stereotype.Service;
import whustore.dao.OrderDao;
import whustore.data.RandomNumber;
import whustore.model.Cart;
import whustore.model.CustomerInfo;
import whustore.model.Order;
import whustore.model.Product;

import java.util.*;

@Service
public class OrderService implements OrderServiceIntf {
    private OrderDao orderDao = new OrderDao();
    CartService cartService = new CartService();

    public List<Order> getAllOrderByIduser(int idUser) {
        return orderDao.getOrderList(idUser);
    }

    public Order getOrderByIduser(int orderId, int userId) {
        return orderDao.getOrderIduser(orderId, userId);
    }

    public boolean orderPaid(int orderId) {
        return orderDao.orderPaid(orderId);
    }

    public Order addOrderByCart(Cart cart, CustomerInfo info) {
        // Get items from cart and set to order
        Order order = new Order();
        order.setIduser(cart.getUserID());
        order.setItems(cart.getItems());
        order.setIdOrder(RandomNumber.getRandomNumber());
        order.setInfo(info);
        return orderDao.addOrder(order) ? order : null;
    }

    public Order addAnOrder(Order order){
        return orderDao.addOrder(order) ? order : null;
    }


    public List<Order> getOrdersByidteam (int idteam){
        return null;
        //        return dao.getOrdersByidteam(idteam);
    }

    public List<Order> addOrderByIdTeam(Cart cart, CustomerInfo info){
        List<Order> orderList = new ArrayList<>();
        Map<Product, Integer> map = cart.getItems();
        int cartSize =  cart.getItems().size();
        for(int j = 0; j < cartSize; j++){
            Iterator it = map.entrySet().iterator();
            Order cur = new Order();
            cur.setIduser(cart.getUserID());
            cur.setIdOrder(RandomNumber.getRandomNumber());
            cur.setInfo(info);

            Map<Product, Integer> items = new HashMap<>();
            Product p = null;

            if(it.hasNext()){
                Map.Entry me = (Map.Entry) it.next();
                p = (Product) me.getKey();
                it.remove();
                items.put(p, (Integer) me.getValue());
                cur.setIdTeam(p.getTeamID());
                while(it.hasNext()){
                    me = (Map.Entry) it.next();
                    Product product = (Product) me.getKey();
                    Integer amount = (Integer) me.getValue();
                    if(product.getTeamID() == p.getTeamID()){
                        items.put(product, amount);
                        it.remove();
                    }
                }
                cur.setItems(items);
            }
            if(cur.getItems() == null){
                break;
            }



/*
            for (Product product : map.keySet()){
                if(product.getTeamID() == p.getTeamID()){
                    items.put(product, map.get(product));
                    map.remove(product);
                 }
                cur.setItems(items);
            }
*/
            if(addAnOrder(cur) != null){
                orderList.add(cur);
            }
        }
        if(cartService.deleteCartByIdCart(cart.getCartID())){
            System.out.println("删除cart" + cart.getCartID());
        }
        return orderList;
    }
}

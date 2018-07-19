package whustore.service;

import whustore.model.Cart;
import whustore.model.CustomerInfo;
import whustore.model.Order;

import java.util.List;

public interface OrderServiceIntf {
    List<Order> getAllOrderByIduser(int userid);

    Order getOrderByIduser(int orderId, int userId);

    boolean orderPaid(int orderId);

    Order addOrder(Cart cart, CustomerInfo info);

}

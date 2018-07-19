package whustore.service;

import org.springframework.stereotype.Service;
import whustore.dao.OrderDao;
import whustore.data.RandomNumber;
import whustore.model.Cart;
import whustore.model.CustomerInfo;
import whustore.model.Order;

import java.util.List;

@Service
public class OrderService implements OrderServiceIntf {
    private OrderDao orderDao = new OrderDao();

    public List<Order> getAllOrderByIduser(int idUser) {
        return orderDao.getOrderList(idUser);
    }

    public Order getOrderByIduser(int orderId, int userId) {
        return orderDao.getOrderIduser(orderId, userId);
    }

    public boolean orderPaid(int orderId) {
        return orderDao.orderPaid(orderId);
    }

    public Order addOrder(Cart cart, CustomerInfo info) {
        // Get items from cart and set to order
        Order order = new Order();
        order.setIduser(cart.getUserID());
        order.setItems(cart.getItems());
        order.setIdOrder(RandomNumber.getRandomNumber());
        order.setInfo(info);
        return orderDao.addOrder(order, cart.getCartID()) ? order : null;


    }


    public List<Order> getOrdersByidteam (int idteam){ return dao.getOrdersByidteam(idteam);}
}

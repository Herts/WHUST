package whustore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whustore.dao.OrderDao;
import whustore.model.Order;

import java.util.List;

@Service
public class OrderService {
    OrderDao dao = new OrderDao();

    public List<Order> getUserOrderList(int userid) {
        return dao.getOrderlist(userid);
    }

    public Order getOrder(int orderId, int userId) {
        return dao.getOrder(orderId, userId);
    }

    public boolean oderPaid(int orderId)
    {
        return dao.orderPaid(orderId);
    }
}

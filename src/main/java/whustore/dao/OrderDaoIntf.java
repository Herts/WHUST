package whustore.dao;

import whustore.model.Order;

import java.util.List;

interface OrderDaoIntf {
    List<Order> getOrderList(int id, String userType);

    boolean addOrder(Order order);

    boolean deleteOrder(int idorder);

    boolean orderPaid(int orderId);

    Order getOrderIduser(int orderId, int userID);


}

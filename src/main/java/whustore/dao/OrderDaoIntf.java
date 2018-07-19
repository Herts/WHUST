package whustore.dao;

import whustore.model.Order;

import java.util.List;

interface OrderDaoIntf {
    List<Order> getOrderList(int userID);

    boolean addOrder(Order order, int idcart);

    boolean deleteOrder(int idorder);

    boolean orderPaid(int orderId);

    Order getOrderIduser(int orderId, int userID);


}

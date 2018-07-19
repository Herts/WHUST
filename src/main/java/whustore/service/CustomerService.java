package whustore.service;

import org.springframework.stereotype.Service;
import whustore.dao.CustomerDao;
import whustore.model.Customer;
import whustore.model.User;

@Service
public class CustomerService {
    CustomerDao customerDao = new CustomerDao();

    /**
     * 获取Customer实体类
     *
     * @param user
     * @return
     */
    public Customer getCustomer(User user) {
        return customerDao.getCustomer(user);
    }

    /**
     * 维护Customer实体类
     *
     * @param customer
     * @param user
     * @return
     */
    public boolean modifyCustomer(Customer customer, User user) {
        return customerDao.modifyCustomer(customer, user);
    }
}

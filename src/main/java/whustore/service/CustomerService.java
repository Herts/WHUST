package whustore.service;

import org.springframework.stereotype.Service;
import whustore.dao.CustomerDao;
import whustore.model.Customer;
import whustore.model.User;

@Service
public class CustomerService {

    /**
     * 获取Customer实体类
     * @param user
     * @return
     */
    public Customer getCustomer(User user) {
        CustomerDao customerDao = new CustomerDao();
        Customer customer = customerDao.getCustomer(user);
        return customer;
    }

    /**
     * 维护Customer实体类
     * @param customer
     * @param user
     * @return
     */
    public boolean modifyCustomer(Customer customer, User user){
        CustomerDao customerDao = new CustomerDao();
        return customerDao.modifyCustomer(customer,user);
    }
}

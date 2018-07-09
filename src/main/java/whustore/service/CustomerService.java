package whustore.service;

import org.springframework.stereotype.Service;
import whustore.dao.CustomerDao;
import whustore.model.Customer;
import whustore.model.User;

@Service
public class CustomerService {

    public Customer getCustomer(User user) {
        CustomerDao customerDao = new CustomerDao();
        Customer customer = customerDao.getCustomer(user);
        return customer;
    }

    public boolean modifyCustomer(Customer customer, User user){
        CustomerDao customerDao = new CustomerDao();
        return customerDao.modifyCustomer(customer,user);
    }
}

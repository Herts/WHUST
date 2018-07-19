package whustore.service;


import whustore.model.Customer;
import whustore.model.CustomerInfo;

public interface CustomerInfoServiceIntf {
    int setCustomerInfo(String name, String addr, String tel, int iduser);

    CustomerInfo getLastCustomerInfoByIduser(int iduser);

    int initializeCustomerInfo(Customer customer, int iduser);
}

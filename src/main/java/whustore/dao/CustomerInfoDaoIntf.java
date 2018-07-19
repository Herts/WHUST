package whustore.dao;

import whustore.model.Customer;
import whustore.model.CustomerInfo;

public interface CustomerInfoDaoIntf {
    int setCustomerInfo(String name, String addr, String tel, int iduser);

    int isCustomerInfoUnique(String name, String addr, String tel, int iduser);

    CustomerInfo getLastCustomerInfoByIduser(int iduser);

    int initializeCustomerInfo(Customer customer, int iduser);
}

package whustore.service;

import org.springframework.stereotype.Service;
import whustore.dao.CustomerInfoDao;
import whustore.model.Customer;
import whustore.model.CustomerInfo;

@Service
public class CustomerInfoService implements CustomerInfoServiceIntf {
    private CustomerInfoDao infoDao = new CustomerInfoDao();

    public int setCustomerInfo(String name, String addr, String tel, int iduser) {
        int idCustomerInfo = isInfoUnique(name, addr, tel, iduser);
        if(idCustomerInfo == -1){
            return infoDao.setCustomerInfo(name, addr, tel, iduser);
        }
        return idCustomerInfo;
    }

    public CustomerInfo setAndGetCustomerInfo(String name, String addr, String tel, int iduser) {
       return getCustomerInfoByIdcustomerInfo(setCustomerInfo(name, addr, tel, iduser));
    }



    private int isInfoUnique(String name, String addr, String tel, int idUser) {
        return infoDao.isCustomerInfoUnique(name, addr, tel, idUser);
    }

    public CustomerInfo getLastCustomerInfoByIduser(int idUser){
        return infoDao.getLastCustomerInfoByIduser(idUser);
    }

    public int initializeCustomerInfo(Customer customer, int idUser){
        return infoDao.initializeCustomerInfo(customer, idUser);
    }

    public CustomerInfo initAndGetCustomerInfo(Customer customer, int idUser) {
        return getCustomerInfoByIdcustomerInfo(initializeCustomerInfo(customer, idUser));
    }

    public CustomerInfo getCustomerInfoByIdcustomerInfo(int id){
        return infoDao.getCustomerInfoByIdcustomerInfo(id);
    }



}

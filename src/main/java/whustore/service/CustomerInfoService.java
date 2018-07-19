package whustore.service;

import org.springframework.stereotype.Service;
import whustore.dao.CustomerInfoDao;
import whustore.model.Customer;
import whustore.model.CustomerInfo;

@Service
public class CustomerInfoService implements CustomerInfoServiceIntf {
    private CustomerInfoDao infoDao = new CustomerInfoDao();

    public int setCustomerInfo(String name, String addr, String tel, int iduser) {
        int idorderitem = isInfoUnique(name, addr, tel, iduser);
        if(idorderitem == -1){
            return infoDao.setCustomerInfo(name, addr, tel, iduser);
        }
        return idorderitem;
    }

    private int isInfoUnique(String name, String addr, String tel, int iduser) {
        return infoDao.isCustomerInfoUnique(name, addr, tel, iduser);
    }

    public CustomerInfo getLastCustomerInfoByIduser(int iduser){
        return infoDao.getLastCustomerInfoByIduser(iduser);
    }

    public int initializeCustomerInfo(Customer customer, int iduser){
        return infoDao.initializeCustomerInfo(customer, iduser);
    }


}

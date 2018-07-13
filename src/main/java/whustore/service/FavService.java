package whustore.service;

import org.springframework.stereotype.Service;
import whustore.dao.FavDao;

@Service
public class FavService {
    FavDao favDao = new FavDao();

    public int changeState(int iduser, int idproduct){
        return favDao.changeFavState(iduser, idproduct);
    }
}

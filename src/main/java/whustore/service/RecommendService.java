package whustore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whustore.dao.RecommendDao;
import whustore.model.Recommend;

@Service
public class RecommendService {
    @Autowired
    RecommendDao dao;

    /**
     * 获取推荐
     *
     * @return
     */
    public Recommend getRecommendation() {
        dao = new RecommendDao();
        return dao.getRecommendation();
    }
}

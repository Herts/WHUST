package whustore.model;


import java.util.ArrayList;
import java.util.List;

public class Recommend {
    private List recommendList = new ArrayList();

    public Recommend() {
    }

    public List getRecommendList() {
        return this.recommendList;
    }

    public void setRecommendList(List recommendList) {
        this.recommendList = recommendList;
    }
}
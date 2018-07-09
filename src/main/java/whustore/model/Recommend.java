package whustore.model;


import java.util.ArrayList;
import java.util.List;

public class Recommend {
    private ArrayList<Product> recommendList = new ArrayList();

    public Recommend() {
    }

    public ArrayList<Product> getRecommendList() {
        return this.recommendList;
    }

    public void setRecommendList(ArrayList<Product> recommendList) {
        this.recommendList = recommendList;
    }
}
package whustore.model;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private int id;
    private String productName;
    private String proIntro;
    private int quantity;
    private int teamID;
    private List<String> type;
    private double price;
    private List<String> picPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getProIntro() {
        return proIntro;
    }

    public void setProIntro(String proIntro) {
        this.proIntro = proIntro;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void picPathAppend(String ppath){
        if (picPath == null)
            picPath =  new ArrayList<String>();
        this.picPath.add(ppath);
    }

    public List<String> getPicPath() {
        return picPath;
    }
}

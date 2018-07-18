package whustore.model;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private int id;
    private String productName;
    private String proIntro;
    private int quantity;
    private int teamID;
    private List<String> types;
    private double price;
    private List<String> picPath;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

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

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> type) {
        this.types = type;
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

    public void picPathAppend(String ppath) {
        if (picPath == null)
            picPath = new ArrayList<String>();
        if (!picPath.contains(ppath))
            this.picPath.add(ppath);
    }

    public boolean typeAppend(String type) {
        if (types == null)
            types = new ArrayList<String>();
        if (!types.contains(type))
            return types.add(type);
        return false;
    }

    public List<String> getPicPath() {
        return picPath;
    }
}

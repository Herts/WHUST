package whustore.model;

import java.util.List;
import java.util.Map;

public class Order {
    private int idOrder;
    private int iduser;
    private Map<Product, Integer> items;
    private String status;
    private String address;
    private String phone;
    private String name;

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Product, Integer> items) {
        this.items = items;
    }

    public double getTotal() {
        double total = 0;
        if (items.size() > 0) {
            for (Product product :
                    items.keySet()) {
                if (product != null)
                    total += product.getPrice() * items.get(product);
            }
        }
        return total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
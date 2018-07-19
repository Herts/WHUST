package whustore.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Order {
    private int idOrder;
    private int iduser;
    private Map<Product, Integer> items;
    private String status;
   /* private String address;
    private String phone;
    private String name;
   */
    private Date createDate;
    private CustomerInfo info;

    public CustomerInfo getInfo() {
        return info;
    }

    public void setInfo(CustomerInfo info) {
        this.info = info;
    }

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

 /*   public String getName() {
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
*/
    public String getOrderItemNames() {
        String names = "";
        int counter = 0;
        for (Product product :
                items.keySet()) {
            names = names + product.getProductName() + "、";
            counter++;
            if (counter > 3) {
                names = names + "等" + items.size() + "件商品,";
                break;
            }
        }
        names = names.substring(0, names.length() - 1);
        return names;
    }

    public String getOrderDetails() {
        String details = "";
        for (Product product :
                items.keySet()) {
            details = details + product.getProductName() +
                    "*" +
                    items.get(product) +
                    "、";
        }
        details = details.substring(0, details.length() - 1);
        return details;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
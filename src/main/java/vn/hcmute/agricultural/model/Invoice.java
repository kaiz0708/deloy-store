package vn.hcmute.agricultural.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invoiceId;
    @ManyToOne
    private User user;
    private Date purchasedDate;
    private Double totalPay;
    private String deliveryAddress;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<CartItem> items;

    public Invoice() {
    }

    public Invoice(Integer invoiceId, User user, Date purchasedDate, Double totalPay, String deliveryAddress, List<CartItem> items) {
        this.invoiceId = invoiceId;
        this.user = user;
        this.purchasedDate = purchasedDate;
        this.totalPay = totalPay;
        this.deliveryAddress = deliveryAddress;
        this.items = items;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public Double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(Double totalPay) {
        this.totalPay = totalPay;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}

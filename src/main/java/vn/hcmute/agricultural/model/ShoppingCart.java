package vn.hcmute.agricultural.model;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

public class ShoppingCart {
    private Integer cartId;
    private Integer userId;
    private Date purchasedDate;
    private Double totalPay;
    private Integer cartStatus;

    @OneToMany(orphanRemoval = true)
    private List<CartItem> items;

    public ShoppingCart() {
    }

    public ShoppingCart(Integer cartId, Integer userId, Date purchasedDate, Double totalPay, Integer cartStatus, List<CartItem> items) {
        this.cartId = cartId;
        this.userId = userId;
        this.purchasedDate = purchasedDate;
        this.totalPay = totalPay;
        this.cartStatus = cartStatus;
        this.items = items;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(Integer cartStatus) {
        this.cartStatus = cartStatus;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}

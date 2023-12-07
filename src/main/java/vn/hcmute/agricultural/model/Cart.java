package vn.hcmute.agricultural.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartID")
    private Integer cartID;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<CartItem> cartItems;

    public Cart() {
    }

    public Cart(Integer cartID, User user, List<CartItem> items) {
        this.cartID = cartID;
        this.user = user;
        this.cartItems = items;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public User getUserID() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}

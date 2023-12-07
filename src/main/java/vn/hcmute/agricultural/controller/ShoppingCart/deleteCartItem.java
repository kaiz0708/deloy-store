package vn.hcmute.agricultural.controller.ShoppingCart;

import vn.hcmute.agricultural.DAO.CartDAO;
import vn.hcmute.agricultural.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/deleteCartItem"})
public class deleteCartItem extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer productId = Integer.parseInt(req.getParameter("productId"));

        ShoppingCart shoppingCart = getSessionCart(req, resp);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedUser");

        for (int i = 0; i < shoppingCart.getItems().size(); i++) {
            CartItem item = shoppingCart.getItems().get(i);
            if (item.getProduct().getProductId().equals(productId)) {
                shoppingCart.getItems().remove(item);
            }
        }
        Cart cart = new Cart();
        try {
            cart = CartDAO.getInstance().getCartByUser(user);
            cart.setCartItems(shoppingCart.getItems());
            CartDAO.getInstance().updateCart(cart);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        req.setAttribute("shoppingCart", shoppingCart);
    }

    ShoppingCart getSessionCart (HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
        return cart;
    }
}

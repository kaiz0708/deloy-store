package vn.hcmute.agricultural.controller.ShoppingCart;

import vn.hcmute.agricultural.DAO.CartDAO;
import vn.hcmute.agricultural.DAO.ProductDAO;
import vn.hcmute.agricultural.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@WebServlet(urlPatterns = {"/addToCart"})
public class addToCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/view/login.jsp";

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedUser");
        Cart cart = new Cart();
        try {
            cart = CartDAO.getInstance().getCartByUser(user);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        System.out.println(cart);

        if (user == null) {
            getServletContext().getRequestDispatcher(url).forward(req, resp);
        }
        else {

            Integer productId = Integer.parseInt(req.getParameter("productId"));
            Product product;
            try {
                product = ProductDAO.getInstance().getProductById(productId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            CartItem item = new CartItem();
            item.setProduct(product);
            item.setAmount(1);

            ShoppingCart shoppingCart = getSessionCart(req, resp);



            List<CartItem> cartItemList = shoppingCart.getItems();
            List<CartItem> list = addItemToCart(cartItemList, item, shoppingCart);
            Cart cartNew = new Cart();
            if(cart == null) {
                shoppingCart = new ShoppingCart();
                shoppingCart.setUserId(user.getUserId());
                Date date = new Date();
                shoppingCart.setPurchasedDate(date);
                shoppingCart.setTotalPay(0.0);
                shoppingCart.setItems(list);
                cartNew.setUser(user);
                cartNew.setCartItems(list);
                try {
                    CartDAO.getInstance().addCart(cartNew);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                shoppingCart.setItems(list);
                cart.setCartItems(list);
                try {
                    CartDAO.getInstance().updateCart(cart);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            session.setAttribute("shoppingCart", shoppingCart);
        }
        resp.setStatus(200);

    }
    List<CartItem> addItemToCart(List<CartItem> cartItemList, CartItem item, ShoppingCart shoppingCart) {
        if(cartItemList == null){
            cartItemList.add(item);
        }
        boolean exist = false;
        for (CartItem i : cartItemList) {
            if (i.getProduct().getProductId() == item.getProduct().getProductId()) {
                i.setAmount(i.getAmount() + 1);
                shoppingCart.setTotalPay(shoppingCart.getTotalPay() + i.getProduct().getPrice());
                exist = true;
            }
        }
        if(exist == false){
            cartItemList.add(item);
        }
        return cartItemList;
    }

    ShoppingCart getSessionCart (HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        return shoppingCart;
    }
}

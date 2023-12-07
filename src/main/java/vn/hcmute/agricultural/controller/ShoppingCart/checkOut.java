package vn.hcmute.agricultural.controller.ShoppingCart;

import vn.hcmute.agricultural.DAO.CartDAO;
import vn.hcmute.agricultural.DAO.InvoiceDAO;
import vn.hcmute.agricultural.DAO.ProductDAO;
import vn.hcmute.agricultural.DAO.UserDAO;
import vn.hcmute.agricultural.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@WebServlet(urlPatterns = {"/checkOut"})
public class checkOut extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Check out running......");

        HttpSession session = req.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        User user = (User) session.getAttribute("loggedUser");

        User userAddress;

        try {
            userAddress = UserDAO.getInstance().getUserById(user.getUserId());
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        Integer productId = null;
        Integer amount = null;
        if(req.getParameter("productId").isEmpty() != true && req.getParameter("amount").isEmpty() != true){
            productId = Integer.parseInt(req.getParameter("productId"));
            amount = Integer.parseInt(req.getParameter("amount"));
        }
        List<CartItem> item = shoppingCart.getItems();
        Invoice invoice = new Invoice();
        Cart cart = new Cart();
        try {
            cart = CartDAO.getInstance().getCartByUser(user);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        try{
            if(productId != null){
                System.out.println(productId);
                Product product = ProductDAO.getInstance().getProductById(productId);
                if(product.getQuantity() < amount){
                    resp.sendError(400, "Quantity invalid!");
                }else{
                    product.setQuantity(product.getQuantity() - amount);
                    ProductDAO.getInstance().updateProduct(product);
                }
                Iterator<CartItem> iterator = item.iterator();
                while (iterator.hasNext()) {
                    CartItem obj = iterator.next();
                    if (obj.getProduct().getProductId() == productId) {
                        List<CartItem> itemList = new ArrayList<>();
                        itemList.add(obj);
                        invoice.setItems(itemList);
                        iterator.remove();
                        break;
                    }
                }
                cart.setCartItems(item);
                CartDAO.getInstance().updateCart(cart);
            }else{
                System.out.println("product null");
                for(CartItem i : item){
                    Product product = ProductDAO.getInstance().getProductById(i.getProduct().getProductId());
                    if(product.getQuantity() < i.getAmount()){
                        resp.sendError(400, "Quantity invalid!");
                    }else{
                        product.setQuantity(product.getQuantity() - i.getAmount());
                        ProductDAO.getInstance().updateProduct(product);
                    }
                }
                List<CartItem> itemTotal = new ArrayList<>();
                invoice.setItems(cart.getCartItems());
                cart.setCartItems(itemTotal);
                item = itemTotal;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        invoice.setDeliveryAddress(user.getAddress());
        invoice.setUser(user);
        invoice.setTotalPay(shoppingCart.getTotalPay());
        try {
            InvoiceDAO.getInstance().addInvoice(invoice);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ShoppingCart newCart = new ShoppingCart();
        newCart.setUserId(user.getUserId());
        Date date = new Date();
        newCart.setPurchasedDate(date);
        if(item == null){
            List<CartItem> items = new ArrayList<>();
            newCart.setItems(items);
        }else{
            newCart.setItems(item);
        }
        session.setAttribute("shoppingCart", newCart);

//        getServletContext().getRequestDispatcher("/loadProfile").forward(req, resp);
        resp.setStatus(200);

    }
}

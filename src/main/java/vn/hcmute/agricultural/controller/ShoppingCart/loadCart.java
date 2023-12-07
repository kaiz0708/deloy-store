package vn.hcmute.agricultural.controller.ShoppingCart;

import org.json.JSONArray;
import org.json.JSONObject;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/loadCart"})
public class loadCart extends HttpServlet {
    Double total;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/view/login.jsp";

        // Check session whether user has already signed in or not
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedUser");

        // Redirect to Log in page in session is null
        if (user == null) {
            getServletContext().getRequestDispatcher(url).forward(req, resp);
        }
        else {
            Cart cart = new Cart();
            try {
                cart = CartDAO.getInstance().getCartByUser(user);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
            ShoppingCart shoppingCart = getSessionCart(req, resp);
            if(shoppingCart == null) {
                shoppingCart = new ShoppingCart();
                shoppingCart.setUserId(user.getUserId());
                Date date = new Date();
                shoppingCart.setPurchasedDate(date);
                List<CartItem> items = new ArrayList<>();
                shoppingCart.setItems(cart.getCartItems());
                shoppingCart.setItems(items);

                session.setAttribute("shoppingCart", shoppingCart);


                System.out.println("new");
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                JSONArray jsonArray = printJSONCart(shoppingCart);
                resp.getWriter().write(jsonArray.toString());
            }
            else {
                System.out.println("just load");
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                JSONArray jsonArray = printJSONCart(shoppingCart);
                shoppingCart.setTotalPay(total);
                shoppingCart.setItems(cart.getCartItems());
                session.setAttribute("shoppingCart", shoppingCart);
                resp.getWriter().write(jsonArray.toString());
            }
        }
    }

    ShoppingCart getSessionCart (HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
        return cart;
    }

    JSONArray printJSONCart (ShoppingCart shoppingCart) {

        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("userId", shoppingCart.getUserId());
        jsonObject.put("purchasedDate", shoppingCart.getPurchasedDate());

        double totalPrice = 0;

        for(CartItem item : shoppingCart.getItems()) {
            Product product = null;
            try {
                product = ProductDAO.getInstance().getProductById(item.getProduct().getProductId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            totalPrice += product.getPrice() * item.getAmount();

            JSONObject jsonObjectItem = new JSONObject();

            jsonObjectItem.put("productId", product.getProductId());
            jsonObjectItem.put("productName", product.getProductName());
            jsonObjectItem.put("productImage", product.getImage());
            jsonObjectItem.put("productPrice", product.getPrice());
            jsonObjectItem.put("amount", item.getAmount());

            jsonObject.append("cartItems",jsonObjectItem);
        }
        total = totalPrice;

        jsonObject.put("totalPrice", totalPrice);

        jsonArray.put(jsonObject);

        System.out.println(jsonArray);
        return jsonArray;
    }

}

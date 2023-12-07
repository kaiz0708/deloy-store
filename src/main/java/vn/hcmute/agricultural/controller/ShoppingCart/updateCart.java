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
import java.util.List;

@WebServlet(urlPatterns = {"/updateCart"})
public class updateCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCart shoppingCart = getSessionCart(req, resp);


        Integer productId = Integer.parseInt(req.getParameter("productId"));
        Integer productAmount = Integer.parseInt(req.getParameter("productAmount"));
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedUser");
        List<CartItem> itemCart = shoppingCart.getItems();

        for(CartItem item : itemCart) {
            if (item.getProduct().getProductId().equals(productId)) {
                System.out.println(productAmount);
                item.setAmount(productAmount);
            }
        }
        Cart cart = new Cart();
        try {
            cart = CartDAO.getInstance().getCartByUser(user);
            cart.setCartItems(itemCart);
            CartDAO.getInstance().updateCart(cart);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        session.setAttribute("shoppingCart", shoppingCart);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        JSONArray jsonArray = printJSONCart(shoppingCart);
        resp.getWriter().write(jsonArray.toString());
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

        jsonObject.put("totalPrice", totalPrice);

        jsonArray.put(jsonObject);

        System.out.println(jsonArray);
        return jsonArray;
    }
}

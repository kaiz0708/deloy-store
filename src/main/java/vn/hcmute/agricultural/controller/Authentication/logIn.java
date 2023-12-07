package vn.hcmute.agricultural.controller.Authentication;

import vn.hcmute.agricultural.DAO.UserDAO;
import vn.hcmute.agricultural.model.CartItem;
import vn.hcmute.agricultural.model.ShoppingCart;
import vn.hcmute.agricultural.model.User;

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

@WebServlet(urlPatterns = {"/logIn"}, name = "1234")
public class logIn extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/view/login.jsp";


        String phoneNumber = (String) req.getParameter("phonenumber");
        String password = (String) req.getParameter("userPassword");

        System.out.println(phoneNumber);
        System.out.println(password);

        User user = null;
        try {
            user = UserDAO.getInstance().userLogin(phoneNumber, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (user != null) {
            url = "/view/index.jsp";
            HttpSession session = req.getSession();
            session.setAttribute("loggedUser", user);

            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUserId(user.getUserId());
            Date date = new Date();
            shoppingCart.setPurchasedDate(date);
            List<CartItem> items = new ArrayList<>();
            shoppingCart.setItems(items);

            session.setAttribute("shoppingCart", shoppingCart);
        }
        else {
            String message = "Wrong username or password !";
            req.setAttribute("logInMessage", message);
        }
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}

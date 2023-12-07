package vn.hcmute.agricultural.controller.profile;

import vn.hcmute.agricultural.DAO.UserDAO;
import vn.hcmute.agricultural.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/updateProfile"})
public class updateProfile extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedUser");

        String fullName = req.getParameter("fullname");
        String address = req.getParameter("address");
        String phoneNumber = req.getParameter("phonenumber");

        try {
            user.setFullName(fullName);
            user.setAddress(address);
            user.setPhoneNumber(phoneNumber);
            UserDAO.getInstance().updateProfile(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        getServletContext().getRequestDispatcher("/loadProfile").forward(req, resp);
    }
}

package vn.hcmute.agricultural.controller.Authentication;

import vn.hcmute.agricultural.DAO.UserDAO;
import vn.hcmute.agricultural.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/createNewPassword"})
public class createNewPassword extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String newPassword = req.getParameter("password");
        String email = (String) session.getAttribute("forgetPassEmail");

        try {
            User user = UserDAO.getInstance().getUserByEmail(email);
            user.setUserPassword(newPassword);

            UserDAO.getInstance().updateProfile(user);
            getServletContext().getRequestDispatcher("/view/login.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

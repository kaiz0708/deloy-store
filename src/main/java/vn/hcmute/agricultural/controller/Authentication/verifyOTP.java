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

@WebServlet(urlPatterns = {"/verifyOTP"})
public class verifyOTP extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message;
        Integer otpCode = Integer.parseInt(req.getParameter("otpCode"));

        if(otpCode.equals(SendMail.otpCode)) {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            try {
                UserDAO.getInstance().register(user);
                message = "Register successfully";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {
            message = "OTP is not correct";
        }
        req.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/view/login.jsp").forward(req,resp);
    }
}

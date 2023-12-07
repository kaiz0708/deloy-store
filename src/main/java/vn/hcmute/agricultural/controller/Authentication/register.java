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
import java.util.Date;

@WebServlet(urlPatterns = {"/register"})
public class register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String message = null;
        String url = "/view/register.jsp";

        String fullName = req.getParameter("fullName");
        String phoneNumber = req.getParameter("phone");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User();
        user.setFullName(fullName);
        user.setPhoneNumber(phoneNumber);
        user.setRoles(1);
        user.setUserStatus(1);
        user.setUserPassword(password);
        user.setEmail(email);

        Date date = new Date();
        user.setCreatedDate(date);

        try{
            if (UserDAO.getInstance().checkUserByEmail(email)){
                System.out.println("!1");
                message = "This email has already registered";
            }
            else if(UserDAO.getInstance().checkUserByPhone(phoneNumber)) {
                System.out.println("!2");
                message = "This phone number has already registered";
            }
            else {
                System.out.println("!3");
                SendMail.sendMail(email);
                message = "OTP has been sent to " + email;
                url = "/view/register_OTP.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        req.setAttribute("user", user);
        req.setAttribute("message", message);
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}

package vn.hcmute.agricultural.controller.Authentication;

import vn.hcmute.agricultural.DAO.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/getOTPPassword"})
public class getOTPPassword extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String message;
        String url;
        HttpSession session = req.getSession();
        boolean isEmailExisted = false;
        try {
            if(UserDAO.getInstance().checkUserByEmail(email)){
                isEmailExisted = true;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(isEmailExisted){
            SendMail.sendMailForgetPass(email);
            session.setAttribute("forgetPassEmail", email);
            url = "/view/checkotp.jsp";
        }
        else {
            message = "Email has not been registered";
            req.setAttribute("message", message);
            url = "/view/forgotpassword.jsp";
        }
        getServletContext().getRequestDispatcher(url).forward(req, resp);

    }
}

package vn.hcmute.agricultural.controller.Authentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/checkOTPPass"})
public class checkOTPPass extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer otpCode = Integer.parseInt(req.getParameter("otpCode"));
        String url;
        if(otpCode.equals(SendMail.otpCode)){
            url = "/view/createnewpass.jsp";
        }
        else {
            url = "/view/checkotp.jsp";
            String message = "OTP is wrong";
            req.setAttribute("message", message);
        }
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}

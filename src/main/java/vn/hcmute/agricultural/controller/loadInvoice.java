package vn.hcmute.agricultural.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import vn.hcmute.agricultural.DAO.InvoiceDAO;
import vn.hcmute.agricultural.model.Invoice;
import vn.hcmute.agricultural.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/loadInvoice"})
public class loadInvoice extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedUser");
        try {
            List<Invoice> invoiceList = InvoiceDAO.getInstance().getInvoiceByUser(user);
            JSONArray jsonArray = new JSONArray();
            for(Invoice i : invoiceList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("invoiceId", i.getInvoiceId());
                jsonObject.put("userName", i.getUser().getFullName());
                jsonObject.put("deliveryAddress", i.getDeliveryAddress());
                System.out.println("address :" + i.getDeliveryAddress());
                jsonObject.put("listItems", i.getItems());
                jsonArray.put(jsonObject);
            }
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            resp.getWriter().write(jsonArray.toString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

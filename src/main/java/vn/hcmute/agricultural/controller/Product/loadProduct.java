package vn.hcmute.agricultural.controller.Product;


import vn.hcmute.agricultural.DAO.ProductDAO;
import vn.hcmute.agricultural.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import org.json.*;
import vn.hcmute.agricultural.model.User;

@WebServlet(urlPatterns = {"/loadProduct"})
public class loadProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/view/products.jsp";

        HttpSession session = req.getSession();
        User loggedUser = (User) session.getAttribute("loggedUser");

        if(loggedUser == null) {
            url = "/view/login.jsp";
        }
        else {
            List<Product> listProduct = null;
            try {
                listProduct = ProductDAO.getInstance().getActiveProduct();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            req.setAttribute("listProduct", listProduct);
        }
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<Product> productList = ProductDAO.getInstance().getProduct();
            JSONArray jsonArray = new JSONArray();

            for(Product product : productList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("productId", product.getProductId());
                jsonObject.put("productName", product.getProductName());
                jsonObject.put("price", product.getPrice());
                jsonObject.put("gender", product.getGender());
                jsonObject.put("color", product.getColor());
                jsonObject.put("brand", product.getBrand().getBrandName());
                jsonObject.put("category", product.getCategory().getCatName());
                jsonObject.put("productStatus", product.getProductStatus());
                jsonObject.put("image", product.getImage());

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

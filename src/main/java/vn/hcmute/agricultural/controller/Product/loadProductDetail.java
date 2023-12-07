package vn.hcmute.agricultural.controller.Product;

import org.json.JSONArray;
import org.json.JSONObject;
import vn.hcmute.agricultural.DAO.ProductDAO;
import vn.hcmute.agricultural.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/loadProductDetail"})
public class loadProductDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer productId = Integer.parseInt(req.getParameter("productId"));

        try {
            Product product = ProductDAO.getInstance().getProductById(productId);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("brand", product.getBrand().getBrandName());
            jsonObject.put("category", product.getCategory().getCatName());
            jsonObject.put("description", product.getDescription());
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonArray.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

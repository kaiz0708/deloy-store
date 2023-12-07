package vn.hcmute.agricultural.controller.Product;

import vn.hcmute.agricultural.DAO.ProductDAO;
import vn.hcmute.agricultural.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/deleteProduct"})
public class deleteProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer productId = Integer.parseInt(req.getParameter("productId"));

        try {
            Product product = ProductDAO.getInstance().getProductById(productId);
            product.setProductStatus("Inactive");

            ProductDAO.getInstance().updateProduct(product);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

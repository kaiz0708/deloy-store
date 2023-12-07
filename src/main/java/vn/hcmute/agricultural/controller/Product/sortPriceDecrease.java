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
import java.util.List;

@WebServlet(urlPatterns = {"/sortPriceDe"})

public class sortPriceDecrease extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Product> productList = (List<Product>) req.getAttribute("listProduct");
        if(productList != null) {
            System.out.println("work");
        }

        List<Product> listProduct = null;
        try {
            listProduct = ProductDAO.getInstance().getProduct();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for(int i = 0; i < listProduct.size(); i++) {
            for(int j = i + 1; j < listProduct.size(); j++) {
                if(listProduct.get(i).getPrice() < listProduct.get(j).getPrice()) {
                    Product temp = listProduct.get(i);
                    listProduct.set(i, listProduct.get(j));
                    listProduct.set(j, temp);
                }
            }
        }

        JSONArray jsonArray = new JSONArray();
        for (Product product : listProduct) {
            JSONObject jsonProduct = new JSONObject();
            jsonProduct.put("productId", product.getProductId());
            jsonProduct.put("productName", product.getProductName());
            jsonProduct.put("price", product.getPrice());
            jsonProduct.put("images", product.getImage());
            //jsonProduct.put("sizes", product.getSizes());
            jsonArray.put(jsonProduct);
        }
        System.out.println(jsonArray);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().write(jsonArray.toString());
    }
}

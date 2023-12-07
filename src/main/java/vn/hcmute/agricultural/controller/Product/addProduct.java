package vn.hcmute.agricultural.controller.Product;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.common.collect.ImmutableMap;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.StorageClient;
import vn.hcmute.agricultural.DAO.BrandDAO;
import vn.hcmute.agricultural.DAO.CategoryDAO;
import vn.hcmute.agricultural.DAO.ProductDAO;
import vn.hcmute.agricultural.model.Brand;
import vn.hcmute.agricultural.model.Category;
import vn.hcmute.agricultural.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(urlPatterns = {"/addProduct"})
@MultipartConfig
public class addProduct extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String downloadUrlImage;
        PrintWriter out = resp.getWriter();

        String productName = req.getParameter("productName");
        String color = req.getParameter("color");
        Double price = Double.valueOf(req.getParameter("price"));
        String state = req.getParameter("gender");
        Part filePart = req.getPart("image");
        String description = req.getParameter("description");
        Integer quantity = Integer.parseInt(req.getParameter("quantity"));
        String brandName = req.getParameter("brandName");
        String catName = req.getParameter("catName");

        Brand brand;
        Category category;

        try {
            brand = BrandDAO.getInstance().getBrandByName(brandName);
            category = CategoryDAO.getInstance().getCategoryByName(catName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        InputStream serviceAccount;
        {
            try {
                // Check if FirebaseApp is already initialized
                FirebaseApp app;
                if (FirebaseApp.getApps().isEmpty()) {
                    serviceAccount = getServletContext().getResourceAsStream("/WEB-INF/tunetowntest-e968a-firebase-adminsdk-vu7bk-37fd0625ea.json");
                    FirebaseOptions options = new FirebaseOptions.Builder()
                            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                            .setDatabaseUrl("https://tunetowntest-e968a-default-rtdb.asia-southeast1.firebasedatabase.app/")
                            .setStorageBucket("tunetowntest-e968a.appspot.com")
                            .build();
                    app = FirebaseApp.initializeApp(options);
                } else {
                    app = FirebaseApp.getInstance();
                }

                String appCheckToken = Arrays.toString(FirebaseAuth.getInstance(app)
                        .createCustomToken("tunetowntest-e968a")
                        .getBytes());

                // Upload Image to Firebase
                String fileName = filePart.getSubmittedFileName();
                InputStream fileContent = filePart.getInputStream();
                try {
                    Storage storage = StorageClient.getInstance(app).bucket("tunetowntest-e968a.appspot.com").getStorage();

                    BlobInfo blobInfo = BlobInfo.newBuilder("tunetowntest-e968a.appspot.com", "agricultural/" + fileName)
                            .setContentType(filePart.getContentType())
                            .setMetadata(ImmutableMap.of("firebaseStorageDownloadTokens", appCheckToken))
                            .build();

                    // Upload the file to Firebase Storage
                    storage.create(blobInfo, fileContent,
                            Storage.BlobWriteOption.userProject("tunetowntest-e968a"),
                            Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.PUBLIC_READ));

                    // Construct the download URL manually
                    downloadUrlImage = "https://firebasestorage.googleapis.com/v0/b/" +
                            "tunetowntest-e968a.appspot.com" +
                            "/o/" +
                            "agricultural%2F" + fileName +
                            "?alt=media";


                } catch (Exception e) {
                    // Handle any other exception
                    throw new RuntimeException(e);
                } finally {
                    fileContent.close();
                }
                Product product = new Product();
                product.setProductName(productName);
                product.setBrand(brand);
                product.setCategory(category);
                product.setColor(color);
                product.setGender(state);
                product.setImage(downloadUrlImage);
                product.setProductStatus("Active");
                product.setPrice(price);
                product.setDescription(description);
                product.setQuantity(quantity);

                ProductDAO.getInstance().addProduct(product);


            } catch (FirebaseAuthException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        out.println("alert('Redirecting to Admin Page...');");
        out.println("window.location.href='/loadAdminPage';");
    }
}

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
import java.util.Arrays;

@WebServlet(urlPatterns = {"/editProduct"})
@MultipartConfig
public class editProduct extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String downloadUrlImage;

        Integer productId = Integer.parseInt(req.getParameter("editProductId"));
        String productName = req.getParameter("editProductName");
        String color = req.getParameter("editColor");
        String state = req.getParameter("editGender");
        String description = req.getParameter("editDescription");
        String priceText = req.getParameter("editPrice");
        char charToRemove = ',';

        for (int i = 0; i < 2; i++) {
            int charIndex = priceText.indexOf(charToRemove);
            if (charIndex != -1) {
                priceText = priceText.substring(0, charIndex) + priceText.substring(charIndex + 1);
            }
        }

        double price = Double.valueOf(priceText);

        String status = req.getParameter("editStatus");

        String brandName = req.getParameter("editBrand");
        String categoryName = req.getParameter("editCategory");

        Brand brand;
        Category category;

        Product product;

        try{
            brand = BrandDAO.getInstance().getBrandByName(brandName);
            category = CategoryDAO.getInstance().getCategoryByName(categoryName);
            product = ProductDAO.getInstance().getProductById(productId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Part filePart = req.getPart("editImage");

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
                            "agricultural/%2F" + fileName +
                            "?alt=media";


                } catch (Exception e) {
                    // Handle any other exception
                    throw new RuntimeException(e);
                } finally {
                    fileContent.close();
                }
                product.setProductName(productName);
                product.setBrand(brand);
                product.setCategory(category);
                product.setColor(color);
                product.setGender(state);
                product.setDescription(description);
                product.setImage(downloadUrlImage);
                product.setProductStatus(status);
                product.setPrice(price);

                ProductDAO.getInstance().updateProduct(product);


            } catch (FirebaseAuthException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        getServletContext().getRequestDispatcher("/loadAdminPage").forward(req, resp);
    }
}

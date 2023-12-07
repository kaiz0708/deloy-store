package vn.hcmute.agricultural.DAO;


import vn.hcmute.agricultural.connection.DBConnection;
import vn.hcmute.agricultural.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProductDAO {

    private static ProductDAO instance;
    private ProductDAO(){}
    public static ProductDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new ProductDAO();
        }
        return instance;
    }

    public void addProduct (Product product) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(product);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public List<Product> getProduct () {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();

        try {
            String jpql = "SELECT p FROM Product p";
            TypedQuery<Product> query = em.createQuery(jpql, Product.class);
            List<Product> listProduct = query.getResultList();
            return listProduct;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }

    public Product getProductById(Integer productId) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();

        try {
            String jpql = "SELECT p FROM Product p WHERE p.productId =:productId";
            TypedQuery<Product> query = em.createQuery(jpql, Product.class);
            query.setParameter("productId", productId);
            Product product = query.getSingleResult();
            return product;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }
    public List<Product> searchProduct(String searchString) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        try {
            String jpql = "SELECT p FROM Product p WHERE p.productName LIKE :searchString";
            TypedQuery<Product> query = em.createQuery(jpql, Product.class);
            query.setParameter("searchString", "%" + searchString + "%");
            List<Product> productList = query.getResultList();
            return productList;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }
    public void updateProduct (Product product) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            em.merge(product);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public Product findProductByName(String productName) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        try {
            String jpql = "SELECT p FROM Product p WHERE p.productName = :productName";
            TypedQuery<Product> query = em.createQuery(jpql, Product.class);
            query.setParameter("productName", productName);
            Product product = query.getSingleResult();
            return product;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }

    public List<Product> getActiveProduct () {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();

        try {
            String jpql = "SELECT p FROM Product p WHERE p.productStatus = : status";
            TypedQuery<Product> query = em.createQuery(jpql, Product.class);
            query.setParameter("status", "Active");
            List<Product> listProduct = query.getResultList();
            return listProduct;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }
    public List<Product> searchActiveProduct(String searchString) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        try {
            String jpql = "SELECT p FROM Product p WHERE p.productName LIKE :searchString AND p.productStatus = : status";
            TypedQuery<Product> query = em.createQuery(jpql, Product.class);
            query.setParameter("searchString", "%" + searchString + "%");
            query.setParameter("status", "Active");
            List<Product> productList = query.getResultList();
            return productList;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }


}

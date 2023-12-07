package vn.hcmute.agricultural.DAO;

import vn.hcmute.agricultural.connection.DBConnection;
import vn.hcmute.agricultural.model.Cart;
import vn.hcmute.agricultural.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class CartDAO {
    private static CartDAO instance;
    private CartDAO(){}
    public static CartDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new CartDAO();
        }
        return instance;
    }

    public Cart getCartByUser (User user) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();

        try {
            String jpql = "SELECT i FROM Cart i WHERE i.user = : user";
            TypedQuery<Cart> query = em.createQuery(jpql, Cart.class);
            query.setParameter("user", user);
            Cart cartList = query.getSingleResult();
            return cartList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public void addCart(Cart cart) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try{
            em.persist(cart);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public void updateCart (Cart cart) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            em.merge(cart);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }
}

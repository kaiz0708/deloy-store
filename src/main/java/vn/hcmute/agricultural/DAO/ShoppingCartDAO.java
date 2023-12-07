package vn.hcmute.agricultural.DAO;

import vn.hcmute.agricultural.connection.DBConnection;
import vn.hcmute.agricultural.model.ShoppingCart;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class ShoppingCartDAO {

    public void createCart(ShoppingCart cart) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();

        try {
            em.persist(cart);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        } finally {
            em.close();
        }

    }

    public ShoppingCart getCartByUserId (Integer userId) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        try {
            String jpql = "SELECT sc FROM ShoppingCart sc WHERE sc.userId = :userId";
            TypedQuery<ShoppingCart> query = em.createQuery(jpql, ShoppingCart.class);
            query.setParameter("userId", userId);

            ShoppingCart shoppingCart = query.getSingleResult();
            return shoppingCart;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}

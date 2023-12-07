package vn.hcmute.agricultural.DAO;

import vn.hcmute.agricultural.connection.DBConnection;
import vn.hcmute.agricultural.model.User;

import javax.persistence.*;

public class UserDAO {

    private static UserDAO instance;
    private UserDAO() {

    }

    public static UserDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }


    public User userLogin (String phoneNumber, String password) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();

        try{
            String jpql = "SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber " +
                    "                           AND u.userPassword = :password ";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("phoneNumber", phoneNumber);
            query.setParameter("password", password);

            User user = query.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public User getUserById (Integer userId) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.userId = :userId";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("userId", userId);

            User user = query.getSingleResult();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public void updateProfile (User user) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.merge(user);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public void register (User user) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        try {
            em.persist(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public boolean checkUserByEmail (String email) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.email = :email";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("email", email);

            User user = query.getSingleResult();
            if(user != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean checkUserByPhone (String phoneNumber) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("phoneNumber", phoneNumber);

            User user = query.getSingleResult();
            if(user != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }
    public User getUserByEmail (String email) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.email = :email";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("email", email);

            User user = query.getSingleResult();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}

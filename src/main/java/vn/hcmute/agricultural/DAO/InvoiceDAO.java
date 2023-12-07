package vn.hcmute.agricultural.DAO;

import vn.hcmute.agricultural.connection.DBConnection;
import vn.hcmute.agricultural.model.Invoice;
import vn.hcmute.agricultural.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class InvoiceDAO {
    private static InvoiceDAO instance;
    private InvoiceDAO(){}
    public static InvoiceDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new InvoiceDAO();
        }
        return instance;
    }

    public List<Invoice> getInvoiceByUser (User user) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();

        try {
            String jpql = "SELECT i FROM Invoice i WHERE i.user = : user";
            TypedQuery<Invoice> query = em.createQuery(jpql, Invoice.class);
            query.setParameter("user", user);

            List<Invoice> invoiceList = query.getResultList();
            return invoiceList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public void addInvoice(Invoice invoice) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();
            em.merge(invoice);
            transaction.commit();
        } catch (Exception e){
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}

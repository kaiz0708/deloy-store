package vn.hcmute.agricultural.connection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBConnection {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("railway");

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}

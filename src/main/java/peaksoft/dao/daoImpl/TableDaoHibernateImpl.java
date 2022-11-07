package peaksoft.dao.daoImpl;

import org.hibernate.Session;
import peaksoft.config.HibernateConfig;
import peaksoft.dao.dao.TableDao;

public class TableDaoHibernateImpl implements TableDao {
    @Override
    public void createTable() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.getTransaction().commit();
            System.out.println("Tables were created successfully");
        }
    }

    public void dropTable() {
        HibernateConfig.shutDown();
        System.out.println("Tables were dropped successfully");
    }
}

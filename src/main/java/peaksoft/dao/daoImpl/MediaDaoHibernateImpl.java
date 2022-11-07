package peaksoft.dao.daoImpl;

import org.hibernate.Session;
import peaksoft.config.HibernateConfig;
import peaksoft.dao.dao.MediaDao;
import peaksoft.model.Garage;
import peaksoft.model.Socialmedia;
import java.util.List;

public class MediaDaoHibernateImpl implements MediaDao {
    @Override
    public void saveSocialMedia(Socialmedia socialmedia) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(socialmedia);
            session.getTransaction().commit();
            System.out.println("A new SocialMedia " + socialmedia.getSocialMedia() + " was inserted into the table");
        }
    }

    @Override
    public void updateSocialNedia(Socialmedia socialmedia) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            Socialmedia socialmedia1 = session.find(Socialmedia.class, socialmedia.getId());
            if (socialmedia1 != null) {
                socialmedia.setPeople(socialmedia1.getPeople());
                session.merge(socialmedia);
                System.out.println("A SocialMedia with Id-" + socialmedia.getId() + " was successfully updated");
                session.getTransaction().commit();
            } else {
                System.out.println("We couldn't find SocialMedia with Id-" + socialmedia.getId());
            }
        }
    }

    @Override
    public Socialmedia getById(Long socialMediaId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            Socialmedia socialmedia = session.get(Socialmedia.class, socialMediaId);
            if (socialmedia != null) {
                System.out.println("SocialMedia with id: " + socialMediaId + " successfully found");
            } else {
                System.out.println("We couldn't find SocialMedia with id " + socialMediaId);
            }
            session.getTransaction().commit();
            return socialmedia;
        }
    }

    @Override
    public List<Socialmedia> getAll() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Socialmedia> socialmedia = session.createQuery("select s from Socialmedia s", Socialmedia.class).getResultList();
            session.getTransaction().commit();
            return socialmedia;
        }
    }

    @Override
    public void deleteById(Long socialMediaId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            Socialmedia socialmedia = session.get(Socialmedia.class, socialMediaId);
            if (socialmedia != null) {
                session.remove(socialmedia);
                session.getTransaction().commit();
                System.out.println("Current id: " + socialMediaId + " was deleted from the table");
            } else {
                System.out.println("We couldn't find SocialMedia with id " + socialMediaId);
            }
        }
    }
}

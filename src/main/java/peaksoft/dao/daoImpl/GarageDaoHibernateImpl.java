package peaksoft.dao.daoImpl;

import org.hibernate.Session;
import peaksoft.config.HibernateConfig;
import peaksoft.dao.dao.GarageDao;
import peaksoft.model.Car;
import peaksoft.model.Garage;
import java.util.List;

public class GarageDaoHibernateImpl implements GarageDao {
    @Override
    public void saveGarage(Garage garage) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(garage);
            session.getTransaction().commit();
            System.out.println("A new Garage " + garage.getName() + " was inserted into the table");
        }
    }

    @Override
    public void updateGarage(Garage garage) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            Garage garage1 = session.find(Garage.class, garage.getId());
            if (garage1 != null) {
                garage.setCar(garage1.getCar());
                session.merge(garage);
                System.out.println("A Garage with Id-" + garage.getId() + " was successfully updated");
                session.getTransaction().commit();
            } else {
                System.out.println("We couldn't find Garage with Id-" + garage.getId());
            }
        }
    }

    @Override
    public Garage getById(Long garageId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            Garage garage = session.get(Garage.class, garageId);
            if (garage != null) {
                System.out.println("Garage with id: " + garageId + " successfully found");
            } else {
                System.out.println("We couldn't find garage with id " + garageId);
            }
            session.getTransaction().commit();
            return garage;
        }
    }

    @Override
    public List<Garage> getAll() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Garage> garages = session.createQuery("select g from Garage g", Garage.class).getResultList();
            session.getTransaction().commit();
            return garages;
        }
    }

    @Override
    public void deleteById(Long garageId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            Garage garage = session.get(Garage.class, garageId);
            if (garage != null) {
                session.remove(garage);
                session.getTransaction().commit();
                System.out.println("Current id: " + garageId + " was deleted from the table");
            } else {
                System.out.println("We couldn't find Garage with id " + garageId);
            }
        }
    }
}

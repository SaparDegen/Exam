package peaksoft.dao.daoImpl;

import org.hibernate.Session;
import peaksoft.config.HibernateConfig;
import peaksoft.dao.dao.CarDao;
import peaksoft.model.Car;
import peaksoft.model.Garage;
import java.util.List;

public class CarDaoHibernateImpl implements CarDao {
    @Override
    public void saveCar(Car car, Long garageId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            Garage garage = session.get(Garage.class, garageId);
            if (garage != null) {
                if (garage.getIsEmpty()) {
                    session.persist(car);
                    car.setGarage(garage);
                    garage.setCar(car);
                    garage.setIsEmpty(false);
                    System.out.println("A new car " + car.getMark() + " was inserted, with GarageId " + garageId);
                    session.getTransaction().commit();
                } else {
                    System.out.println("Garage with Id-" + garageId + " is not empty");
                }
            } else {
                System.out.println("We couldn't find Garage with Id-" + garageId);
            }
        }
    }

    @Override
    public void updateCar(Car car, Long garageId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            Car car1 = session.find(Car.class, car.getId());
            Garage garageOld = car1.getGarage();
            Garage garageNew = session.get(Garage.class, garageId);
            if (car1 != null) {
                car.setPerson(car1.getPerson());
                if (garageNew != null) {
                    if (garageNew.getIsEmpty()) {
                        car.setGarage(garageNew);
                        garageNew.setCar(car);
                        garageNew.setIsEmpty(false);
                        garageOld.setCar(null);
                        garageOld.setIsEmpty(true);
                        session.merge(car);
                        System.out.println("Car with Id-" + car.getId() + " was successfully updated");
                        session.getTransaction().commit();
                    } else {
                        System.out.println("Garage with Id-" + garageId + " is not empty");
                    }
                } else {
                    System.out.println("We couldn't find Garage with Id-" + garageId);
                }
            } else {
                System.out.println("We couldn't find Car with Id-" + car.getId());
            }
        }
    }

    @Override
    public Car getById(Long carId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            Car car = session.get(Car.class, carId);
            if (car != null) {
                System.out.println("Car with id: " + carId + " successfully found");
            } else {
                System.out.println("We couldn't find car with id " + carId);
            }
            session.getTransaction().commit();
            return car;
        }
    }

    @Override
    public List<Car> getAll() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Car> cars = session.createQuery("select c from Car c", Car.class).getResultList();
            session.getTransaction().commit();
            return cars;
        }
    }

    @Override
    public void deleteById(Long carId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            Car car = session.get(Car.class, carId);
            if (car != null) {
                session.remove(car);
                session.getTransaction().commit();
                System.out.println("Current id: " + carId + " was deleted from the table");
            } else {
                System.out.println("We couldn't find Car with id " + carId);
            }
        }
    }
}

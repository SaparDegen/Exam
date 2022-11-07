package peaksoft.service.serviceImpl;

import org.postgresql.util.PSQLException;
import peaksoft.dao.dao.CarDao;
import peaksoft.dao.dao.GarageDao;
import peaksoft.dao.daoImpl.CarDaoHibernateImpl;
import peaksoft.model.Car;
import peaksoft.model.Garage;
import peaksoft.service.service.CarService;

import java.util.List;

public class CarServiceImpl implements CarService {
    CarDao carDao = new CarDaoHibernateImpl();

    @Override
    public void saveCar(Car car, Long garageId) {
        try {
            carDao.saveCar(car, garageId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void updateCar(Car car, Long garageId) {
        carDao.updateCar(car, garageId);
    }

    @Override
    public Car getById(Long carId) {
        return carDao.getById(carId);
    }

    @Override
    public List<Car> getAll() {
        return carDao.getAll();
    }

    @Override
    public void deleteById(Long carId) {
        carDao.deleteById(carId);
    }
}

package peaksoft.dao.dao;

import org.postgresql.util.PSQLException;
import peaksoft.model.Car;
import peaksoft.model.Company;
import java.util.List;

public interface CarDao {
    void saveCar(Car car, Long garageId);

    void updateCar(Car car, Long garageId);

    Car getById(Long carId);

    List<Car> getAll();

    void deleteById(Long carId);
}

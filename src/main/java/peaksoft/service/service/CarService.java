package peaksoft.service.service;

import org.postgresql.util.PSQLException;
import peaksoft.model.Car;

import java.util.List;

public interface CarService {
    void saveCar(Car car, Long garageId);

    void updateCar(Car car, Long garageId);

    Car getById(Long carId);

    List<Car> getAll();

    void deleteById(Long carId);
}

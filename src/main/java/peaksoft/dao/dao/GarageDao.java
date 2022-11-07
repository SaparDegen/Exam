package peaksoft.dao.dao;

import peaksoft.model.Car;
import peaksoft.model.Garage;
import java.util.List;

public interface GarageDao {
    void saveGarage(Garage garage);

    void updateGarage(Garage garage);

    Garage getById(Long garageId);

    List<Garage> getAll();

    void deleteById(Long garageId);
}

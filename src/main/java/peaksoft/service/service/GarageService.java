package peaksoft.service.service;

import peaksoft.model.Garage;
import java.util.List;

public interface GarageService {

    void saveGarage(Garage garage);

    void updateGarage(Garage garage);

    Garage getById(Long garageId);

    List<Garage> getAll();

    void deleteById(Long garageId);
}

package peaksoft.service.serviceImpl;

import peaksoft.dao.dao.GarageDao;
import peaksoft.dao.daoImpl.GarageDaoHibernateImpl;
import peaksoft.model.Garage;
import peaksoft.service.service.GarageService;

import java.util.List;

public class GarageServiceImpl implements GarageService {

    GarageDao garageDao = new GarageDaoHibernateImpl();

    @Override
    public void saveGarage(Garage garage) {
        garageDao.saveGarage(garage);
    }

    @Override
    public void updateGarage(Garage garage) {
        garageDao.updateGarage(garage);
    }

    @Override
    public Garage getById(Long garageId) {
        return garageDao.getById(garageId);
    }

    @Override
    public List<Garage> getAll() {
        return garageDao.getAll();
    }

    @Override
    public void deleteById(Long garageId) {
        garageDao.deleteById(garageId);
    }
}

package peaksoft.service.serviceImpl;

import peaksoft.dao.dao.PersonDao;
import peaksoft.dao.daoImpl.PersonDaoHibernateImpl;
import peaksoft.model.Person;
import peaksoft.service.service.PersonService;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao = new PersonDaoHibernateImpl();

    @Override
    public void savePerson(Person person, Long companyId, Long socialMediaId, Long carId) {
        try {
            personDao.savePerson(person, companyId, socialMediaId, carId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void updatePerson(Person person, Long companyId, Long socialMediaId, Long carId) {
        personDao.updatePerson(person, companyId, socialMediaId, carId);
    }

    @Override
    public Person getById(Long personId) {
        return personDao.getById(personId);
    }

    @Override
    public List<Person> getAll() {
        return personDao.getAll();
    }

    @Override
    public void deleteById(Long personId) {
        personDao.deleteById(personId);
    }

    @Override
    public void assignPersonToSocialMedia(Long personId, Long socialMediaId) {
        personDao.assignPersonToSocialMedia(personId, socialMediaId);
    }
}

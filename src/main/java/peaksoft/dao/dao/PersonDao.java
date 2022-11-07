package peaksoft.dao.dao;

import peaksoft.model.Car;
import peaksoft.model.Person;
import java.util.List;

public interface PersonDao {
    void savePerson(Person person, Long companyId, Long socialMediaId, Long carId);

    void updatePerson(Person person, Long companyId, Long socialMediaId, Long carId);

    Person getById(Long personId);

    List<Person> getAll();

    void deleteById(Long personId);

    void assignPersonToSocialMedia(Long personId, Long socialMediaId);
}

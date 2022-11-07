package peaksoft.dao.daoImpl;

import org.hibernate.Session;
import peaksoft.config.HibernateConfig;
import peaksoft.dao.dao.PersonDao;
import peaksoft.model.*;
import java.util.List;

public class PersonDaoHibernateImpl implements PersonDao {

    @Override
    public void savePerson(Person person, Long companyId, Long socialMediaId, Long carId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            Company company = session.get(Company.class, companyId);
            Socialmedia socialmedia = session.get(Socialmedia.class, socialMediaId);
            Car car = session.get(Car.class, carId);
            if (company != null) {
                if (socialmedia != null) {
                    if (car != null) {
                        person.setCompany(company);
                        company.addPersonToList(person);
                        person.addSocialMediaToList(socialmedia);
                        if (car.getPerson() == null) {
                            person.addCarToList(car);
                            car.setPerson(person);
                        } else {
                            System.out.println("The car-" + carId + " is busy");
                        }
                        session.persist(person);
                        System.out.println("A new person " + person.getName() + " was created successfully");
                        session.getTransaction().commit();
                    } else {
                        System.out.println("We couldn't find Car with Id-" + carId);
                    }
                } else {
                    System.out.println("We couldn't find SocialMedia with Id-" + socialMediaId);
                }
            } else {
                System.out.println("We couldn't find Company with Id-" + companyId);
            }
        }
    }

    @Override
    public void updatePerson(Person person, Long companyId, Long socialMediaId, Long carId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            Person person1 = session.find(Person.class, person.getId());
            if (person1 != null) {
                Company company = session.get(Company.class, companyId);
                Socialmedia socialmedia = session.get(Socialmedia.class, socialMediaId);
                Car car = session.get(Car.class, carId);
                if (company != null) {
                    if (socialmedia != null) {
                        if (car != null) {
                            person.setCompany(company);
                            company.addPersonToList(person);
                            person.addSocialMediaToList(socialmedia);
                            if (car.getPerson() == null) {
                                person.addCarToList(car);
                                car.setPerson(person);
                            } else {
                                System.out.println("The car-" + carId + " is busy");
                            }
                            session.merge(person);
                            System.out.println("Person with Id-" + person.getId() + " was successfully updated");
                            session.getTransaction().commit();
                        } else {
                            System.out.println("We couldn't find Car with Id-" + carId);
                        }
                    } else {
                        System.out.println("We couldn't find SocialMedia with Id-" + socialMediaId);
                    }
                } else {
                    System.out.println("We couldn't find Company with Id-" + companyId);
                }
            } else {
                System.out.println("We couldn't find Person with Id-" + person.getId());
            }
        }
    }

    @Override
    public Person getById(Long personId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            Person person = session.get(Person.class, personId);
            if (person != null) {
                System.out.println("Person with id: " + personId + " successfully found");
            } else {
                System.out.println("We couldn't find Person with id " + personId);
            }
            session.getTransaction().commit();
            return person;
        }
    }

    @Override
    public List<Person> getAll() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Person> people = session.createQuery("select p from Person p", Person.class).getResultList();
            session.getTransaction().commit();
            return people;
        }
    }

    @Override
    public void deleteById(Long personId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            Person person = session.get(Person.class, personId);
            if (person != null) {
                session.remove(person);
                session.getTransaction().commit();
                System.out.println("Current id: " + personId + " was deleted from the table");
            } else {
                System.out.println("We couldn't find Person with id " + personId);
            }
        }
    }

    @Override
    public void assignPersonToSocialMedia(Long personId, Long socialMediaId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            Person person = session.get(Person.class, personId);
            Socialmedia socialmedia = session.get(Socialmedia.class, socialMediaId);
            if (person != null && socialmedia != null) {
                person.addSocialMediaToList(socialmedia);
                session.merge(person);
                System.out.println("Person-" + personId + " was assigned to SocialMedia-" + socialMediaId + " successfully");
                session.getTransaction().commit();
            } else {
                System.out.println("We couldn't find PersonId-" + personId + ", or SocialMediaId-" + socialMediaId);
            }
        }
    }
}

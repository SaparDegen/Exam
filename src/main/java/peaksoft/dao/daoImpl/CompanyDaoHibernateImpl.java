package peaksoft.dao.daoImpl;

import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import peaksoft.config.HibernateConfig;
import peaksoft.dao.dao.CompanyDao;
import peaksoft.model.Car;
import peaksoft.model.Company;
import java.util.List;

public class CompanyDaoHibernateImpl implements CompanyDao {
    @Override
    public void saveCompany(Company company) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(company);
            session.getTransaction().commit();
            System.out.println("A new company " + company.getCompanyName() + " was inserted into the table");
        }
    }

    @Override
    public void updateCompany(Company company) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            Company company1 = session.find(Company.class, company.getId());
            if (company1 != null) {
                company.setPeople(company1.getPeople());
                session.merge(company);
                System.out.println("A Company with Id-" + company.getId() + " was successfully updated");
                session.getTransaction().commit();
            } else {
                System.out.println("We couldn't find Company with Id-" + company.getId());
            }
        }
    }

    @Override
    public Company getById(Long companyId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            Company company = session.get(Company.class, companyId);
            if (company != null) {
                System.out.println("Company with id: " + companyId + " successfully found");
            } else {
                System.out.println("We couldn't find company with id " + companyId);
            }
            session.getTransaction().commit();
            return company;
        }
    }

    @Override
    public List<Company> getAll() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Company> companies = session.createQuery("select c from Company c", Company.class).getResultList();
            session.getTransaction().commit();
            return companies;
        }
    }

    @Override
    public void deleteById(Long companyId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            Company company = session.get(Company.class, companyId);
            if (company != null) {
                session.remove(company);
                session.getTransaction().commit();
                System.out.println("Current id: " + companyId + " was deleted from the table");
            } else {
                System.out.println("We couldn't find Company with id " + companyId);
            }
        }
    }
}

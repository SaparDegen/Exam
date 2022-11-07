package peaksoft.service.serviceImpl;

import jakarta.persistence.PersistenceException;
import org.hibernate.exception.ConstraintViolationException;
import peaksoft.dao.dao.CompanyDao;
import peaksoft.dao.daoImpl.CompanyDaoHibernateImpl;
import peaksoft.model.Company;
import peaksoft.service.service.CompanyService;

import java.sql.SQLException;
import java.util.List;

public class CompanyServiceImpl implements CompanyService {
    CompanyDao companyDao = new CompanyDaoHibernateImpl();

    @Override
    public void saveCompany(Company company) {
        try {
            if (company.getCompanyName().length() > 30) {
                throw new Exception("Length of Company name must be less then 30 char");
            }
            companyDao.saveCompany(company);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateCompany(Company company) {
        companyDao.updateCompany(company);
    }

    @Override
    public Company getById(Long companyId) {
        return companyDao.getById(companyId);
    }

    @Override
    public List<Company> getAll() {
        return companyDao.getAll();
    }

    @Override
    public void deleteById(Long companyId) {
        companyDao.deleteById(companyId);
    }
}

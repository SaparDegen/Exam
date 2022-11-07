package peaksoft.dao.dao;

import peaksoft.model.Car;
import peaksoft.model.Company;
import java.util.List;

public interface CompanyDao {
    void saveCompany(Company company);

    void updateCompany(Company company);

    Company getById(Long companyId);

    List<Company> getAll();

    void deleteById(Long companyId);
}

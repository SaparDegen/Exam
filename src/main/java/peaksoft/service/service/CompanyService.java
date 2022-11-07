package peaksoft.service.service;

import peaksoft.model.Company;
import java.util.List;

public interface CompanyService {
    void saveCompany(Company company);

    void updateCompany(Company company);

    Company getById(Long companyId);

    List<Company> getAll();

    void deleteById(Long companyId);
}

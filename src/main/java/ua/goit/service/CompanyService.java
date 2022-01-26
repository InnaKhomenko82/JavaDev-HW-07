package ua.goit.service;

import ua.goit.models.Company;


public class CompanyService extends ServiceCrud<Company, Long> {

    private static CompanyService instance;

    public CompanyService(Class<Company> classModel) {
        super(classModel);
    }

    public static CompanyService getInstance() {
        if (instance == null) {
            instance = new CompanyService(Company.class);
        }
        return instance;
    }
}

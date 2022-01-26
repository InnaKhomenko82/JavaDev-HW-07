package ua.goit.service;

import ua.goit.models.Company;
import ua.goit.models.Developer;
import ua.goit.models.dto.DevCompanyDto;
import ua.goit.repository.CrudRepository;
import ua.goit.repository.RepositoryFactory;

public class DevCompanyService extends ServiceCrud<DevCompanyDto, Long> implements BaseService<DevCompanyDto, Long>{

    private final CrudRepository<Long,Developer> repository = RepositoryFactory.of(Developer.class);
    private final CompanyService companyService= new CompanyService(Company.class);
    private final DeveloperService developerService = new DeveloperService(Developer.class);

    public DevCompanyService(Class<DevCompanyDto> classModel) {
        super(classModel);
    }

    @Override
    public DevCompanyDto createEntity(DevCompanyDto devCompanyDto) {

        Company company = companyService.findById(devCompanyDto.getCompany())
                .orElseThrow(()->new RuntimeException("Company with id " + devCompanyDto.getCompany() + " not found"));
        Developer developer = Developer.builder()
                .name(devCompanyDto.getName())
                .age(devCompanyDto.getAge())
                .salary(devCompanyDto.getSalary())
                .company(company)
//                .skills()
                .build();

        System.out.println(developer);

        repository.save(developer);

        return super.createEntity(devCompanyDto);
    }

    @Override
    public DevCompanyDto updateEntity(DevCompanyDto devCompanyDto) {

        Company company = companyService.findById(devCompanyDto.getCompany())
                .orElseThrow(()->new RuntimeException("Company with id " + devCompanyDto.getCompany() + " not found"));
        Developer developerEdit = developerService.findById(devCompanyDto.getId())
                .orElseThrow(()->new RuntimeException("Developer with id " + devCompanyDto.getId() + " not found"));

        Developer developer = Developer.builder()
                .id(developerEdit.getId())
                .name(devCompanyDto.getName())
                .age(devCompanyDto.getAge())
                .salary(devCompanyDto.getSalary())
                .company(company)
//                .skills()
                .build();

        System.out.println(developer + " + " + company);

        repository.save(developer);

        return super.updateEntity(devCompanyDto);
    }

}

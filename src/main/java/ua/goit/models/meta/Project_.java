package ua.goit.models.meta;

import ua.goit.models.Company;
import ua.goit.models.Customer;
import ua.goit.models.Developer;
import ua.goit.models.Project;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Project.class)
public abstract class Project_ {

    public static volatile SetAttribute<Project, Company> companies;
    public static volatile SingularAttribute<Project, Integer> cost;
    public static volatile SetAttribute<Project, Developer> developers;
    public static volatile SingularAttribute<Project, String> name;
    public static volatile SingularAttribute<Project, Long> id;
    public static volatile SetAttribute<Project, Customer> customers;
    public static volatile SingularAttribute<Project, Date> projectStart;

    public static final String COMPANIES = "companies";
    public static final String COST = "cost";
    public static final String DEVELOPERS = "developers";
    public static final String NAME = "name";
    public static final String ID = "id";
    public static final String CUSTOMERS = "customers";
    public static final String PROJECT_START = "projectStart";

}
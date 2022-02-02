package ua.goit.models.meta;

import ua.goit.models.Company;
import ua.goit.models.Developer;
import ua.goit.models.Project;
import ua.goit.models.Skill;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Developer.class)
public abstract class Developer_ {

    public static volatile SetAttribute<Developer, Skill> skills;
    public static volatile SetAttribute<Developer, Project> projects;
    public static volatile SingularAttribute<Developer, String> name;
    public static volatile SingularAttribute<Developer, Company> company;
    public static volatile SingularAttribute<Developer, Long> id;
    public static volatile SingularAttribute<Developer, Integer> salary;
    public static volatile SingularAttribute<Developer, Integer> age;

    public static final String SKILLS = "skills";
    public static final String PROJECTS = "projects";
    public static final String NAME = "name";
    public static final String COMPANY = "company";
    public static final String ID = "id";
    public static final String SALARY = "salary";
    public static final String AGE = "age";
}
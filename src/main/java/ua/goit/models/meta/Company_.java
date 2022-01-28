package ua.goit.models;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Company.class)
public abstract class Company_ {

	public static volatile SetAttribute<Company, Project> projects;
	public static volatile SetAttribute<Company, Developer> developers;
	public static volatile SingularAttribute<Company, String> name;
	public static volatile SingularAttribute<Company, Long> quantityStaff;
	public static volatile SingularAttribute<Company, Long> id;

	public static final String PROJECTS = "projects";
	public static final String DEVELOPERS = "developers";
	public static final String NAME = "name";
	public static final String QUANTITY_STAFF = "quantityStaff";
	public static final String ID = "id";

}


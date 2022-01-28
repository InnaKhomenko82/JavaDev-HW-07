package ua.goit.models;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Skill.class)
public abstract class Skill_ {

	public static volatile SingularAttribute<Skill, String> skillsField;
	public static volatile SetAttribute<Skill, Developer> developers;
	public static volatile SingularAttribute<Skill, Long> id;
	public static volatile SingularAttribute<Skill, String> skillsLevel;

	public static final String SKILLS_FIELD = "skillsField";
	public static final String DEVELOPERS = "developers";
	public static final String ID = "id";
	public static final String SKILLS_LEVEL = "skillsLevel";

}


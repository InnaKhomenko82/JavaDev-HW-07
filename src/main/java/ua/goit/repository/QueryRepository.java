package ua.goit.repository;

import ua.goit.models.BaseEntity;
import ua.goit.models.Skill;

import java.util.List;

public interface QueryRepository <E extends BaseEntity> {

    List <E> salaryByProjectName(String projectName, Class T);

    List <E> listDevsInProject(String projectName, Class T);

    List <Skill> listDevsWithSkill(String skillName, Class T);

    List <E> listDevsWithLevel(String levelName, Class T);

    List <E> listOfProjects(Class T);
}

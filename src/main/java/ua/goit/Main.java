package ua.goit;

import ua.goit.models.Project;
import ua.goit.models.Skill;
import ua.goit.repository.QueryRepositoryHibernateImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        QueryRepositoryHibernateImpl repo = new QueryRepositoryHibernateImpl(Project.class);
        List list = repo.listDevsInProject("ShedullerBot", Project.class);
        //repo.listDevsWithSkill("Java", Skill.class);
    }
}

package ua.goit;

import ua.goit.repository.QueryRepositoryHibernateImpl;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try (QueryRepositoryHibernateImpl repo = new QueryRepositoryHibernateImpl()) {
            System.out.println("***salaryByProjectName***");
            System.out.println(repo.salaryByProjectName("ShedullerBot"));
            System.out.println("***listDevsInProject***");
            System.out.println(repo.listDevsInProject("ShedullerBot"));
            System.out.println("***listDevsWithSkill***");
            System.out.println(repo.listDevsWithSkill("Java"));
            System.out.println("***listDevsWithLevel***");
            System.out.println(repo.listDevsWithLevel("senior"));
            System.out.println("***listOfProjects***");
            System.out.println(repo.listOfProjects());
        }
    }
}

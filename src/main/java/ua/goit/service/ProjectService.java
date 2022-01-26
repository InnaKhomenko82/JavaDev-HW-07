package ua.goit.service;

import ua.goit.models.Project;

public class ProjectService extends ServiceCrud<Project, Long>  {

    private static ProjectService instance;

    public ProjectService(Class<Project> classModel) {
        super(classModel);
    }

    public static ProjectService getInstance() {
        if (instance == null) {
            instance = new ProjectService(Project.class);
        }
        return instance;
    }
}

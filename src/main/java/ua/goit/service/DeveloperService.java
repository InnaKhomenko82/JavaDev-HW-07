package ua.goit.service;

import ua.goit.models.Developer;

public class DeveloperService extends ServiceCrud<Developer, Long> {

    private static DeveloperService instance;

    public DeveloperService(Class<Developer> classModel) {
        super(classModel);
    }

    public static DeveloperService getInstance() {
        if (instance == null) {
            instance = new DeveloperService(Developer.class);
        }
        return instance;
    }
}

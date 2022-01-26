package ua.goit.service;

import ua.goit.models.Company;
import ua.goit.models.Skill;

public class SkillService extends ServiceCrud<Skill, Long> {

    private static SkillService instance;

    public SkillService(Class<Skill> classModel) {
        super(classModel);
    }

    public static SkillService getInstance() {
        if (instance == null) {
            instance = new SkillService(Skill.class);
        }
        return instance;
    }

}

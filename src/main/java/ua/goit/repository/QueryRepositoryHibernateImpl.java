package ua.goit.repository;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.sqm.tree.predicate.SqmPredicate;
import ua.goit.models.*;
import ua.goit.models.dto.TestDTO;
import ua.goit.models.meta.Developer_;
import ua.goit.models.meta.Project_;
import ua.goit.util.HibernateSessionFactory;

import javax.persistence.criteria.*;
import java.io.Closeable;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QueryRepositoryHibernateImpl implements Closeable {

    private <K, V> V execute(Map<String, Object> params, Class<K> modelClass,
                             Function<Stream<K>, V> converter) {
        Session session = createSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<K> query = criteriaBuilder.createQuery(modelClass);
        Root<K> projectRoot = query.from(modelClass);
        CriteriaQuery<K> criteriaQuery = query.select(projectRoot);
        if (params != null && !params.isEmpty()) {
            SqmPredicate[] prdcts = params.entrySet().stream()
                    .map(e -> criteriaBuilder.equal(projectRoot.get(e.getKey()), e.getValue()))
                    .toArray(SqmPredicate[]::new);
            criteriaQuery.where(prdcts);
        }
        V result = converter.apply(session.createQuery(query).getResultStream());
        Hibernate.initialize(result);
        closeSession(session);
        return result;
    }

    public List<Developer> test() {
        Map<String, Object> params = new HashMap<>();
        params.put("age", 20);
        params.put("salary", 500);
        return execute(params, Developer.class,
                value -> value.collect(Collectors.toList()));
    }

    public Integer salaryByProjectName(String projectName) {
        return execute(Collections.singletonMap("name", projectName), Project.class,
                value -> value
                        .flatMap(p->p.getDevelopers().stream())
                        .mapToInt(Developer::getSalary)
                        .sum());
        /*
        "SELECT p.name, sum(d.salary) as totalSalary FROM " + databaseSchemaName + "." + "developers d" +
                " INNER JOIN developers_projects dp on d.id = dp.developer_id" +
                " INNER JOIN projects p on p.id = dp.project_id" +
                " where p.name = ?;");
        */
    }

    public Collection<Developer> listDevsInProject(String projectName) {
        return execute(Collections.singletonMap("name", projectName), Project.class,
                value -> value
                        .flatMap(p->p.getDevelopers().stream())
                        .collect(Collectors.toSet()));
        /*
        "SELECT p.name as projectName, d.name as devName\n" +
                "FROM " + databaseSchemaName + "." + "developers d\n" +
                "INNER JOIN developers_projects dp on d.id = dp.developer_id\n" +
                "INNER JOIN projects p on p.id = dp.project_id\n" +
                "where p.name = ?;");
        */
    }

    public List<Developer> listDevsWithSkill(String skillName) {
        return execute(Collections.singletonMap("skillsField", skillName), Skill.class,
                value -> value
                        .flatMap(s->s.getDevelopers().stream())
                        .collect(Collectors.toList()));
        /*
        "SELECT s.field as skillName, d.name as devName\n" +
                "FROM " + databaseSchemaName + "." + "developers d\n" +
                "INNER JOIN developers_skills ds on d.id = ds.developer_id\n" +
                "INNER JOIN skills s on s.id = ds.skill_id\n" +
                "where s.field = ?;");
         */
    }

    public List<Developer> listDevsWithLevel(String levelName) {
        return execute(Collections.singletonMap("skillsLevel", levelName), Skill.class,
                value -> value
                        .flatMap(s->s.getDevelopers().stream())
                        .collect(Collectors.toList()));
        /*
        "SELECT s.level as level, d.name as devName\n" +
                "FROM " + databaseSchemaName + "." + "developers d\n" +
                "INNER JOIN developers_skills ds on d.id = ds.developer_id\n" +
                "INNER JOIN skills s on s.id = ds.skill_id\n" +
                "where s.level = ?;");
         */
    }

    public List<TestDTO> listOfProjects() {
        Session session = createSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TestDTO> query = criteriaBuilder.createQuery(TestDTO.class);
        Root<Project> root = query.from(Project.class);

        Subquery<Long> subquery = query.subquery(Long.class);
        Root<Developer> subRoot = subquery.from(Developer.class);
        SetJoin<Developer, Project> subProjects = subRoot.join(Developer_.projects);
        /*subquery.select(criteriaBuilder.count(subRoot.get(Developer_.salary)));
        subquery.where(criteriaBuilder.equal(root.get(Project_.id), subProjects.get(Project_.id)));*/

        query.multiselect(root.get(Project_.PROJECT_START), root.get(Project_.NAME)
//                , criteriaBuilder.count(subRoot.get(Developer_.SALARY))
        );

        List<TestDTO> resultList = session.createQuery(query).getResultList();

        closeSession(session);
        return resultList;
    }

    /*public Collection<Project> listOfProjects() {

//        Long count = execute(Collections.singletonMap("name", "ShedullerBot"), Project.class,
//                value -> value
//                        .flatMap(p -> p.getDevelopers().stream())
//                        .mapToInt(Developer::getSalary)
//                        .count());
//        System.out.println(count);
        return execute(Collections.emptyMap(), Project.class,
                value -> value.collect(Collectors.toSet()));
        *//*
        "SELECT p.start, p.name, count(d.name) as quantityDevs\n" +
                "FROM " + databaseSchemaName + "." + "projects p\n" +
                "INNER JOIN developers_projects dp on p.id = dp.project_id\n" +
                "INNER JOIN developers d on d.id = dp.developer_id\n" +
                "group by p.name, p.start;");
         *//*
    }*/

    public List <Developer> listOfDevs(){
        return execute(Collections.emptyMap(), Developer.class,
                value -> value.collect(Collectors.toList()));
    }

    public List <Skill> listOfSkillsField(){
        Session session = createSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Skill> query = criteriaBuilder.createQuery(Skill.class);
        Root<Skill> root = query.from(Skill.class);

        query.select(root.get("skillsField")).distinct(true);
        List<Skill> resultList = session.createQuery(query).getResultList();

        closeSession(session);
        return resultList;
    }

    public List <Skill> listOfSkillsLevel(){
        Session session = createSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Skill> query = criteriaBuilder.createQuery(Skill.class);
        Root<Skill> root = query.from(Skill.class);

        query.select(root.get("skillsLevel")).distinct(true);
        List<Skill> resultList = session.createQuery(query).getResultList();

        closeSession(session);
        return resultList;
    }

    @Override
    public void close() throws IOException {
        HibernateSessionFactory.close();
    }

    private Session createSession(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        return session;
    }

    private void closeSession(Session session){
        session.getTransaction().commit();
        session.close();
    }
}

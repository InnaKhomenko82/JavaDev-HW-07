package ua.goit.repository;

import org.hibernate.Session;
import ua.goit.models.*;
import ua.goit.models.Developer_;
import ua.goit.models.Skill_;
import ua.goit.util.HibernateSessionFactory;

import javax.persistence.criteria.*;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class QueryRepositoryHibernateImpl<E extends BaseEntity> implements QueryRepository<E>, Closeable {

    private final Class <E> modelClass;

    public QueryRepositoryHibernateImpl(Class<E> modelClass) {
        this.modelClass = modelClass;
    }


    @Override
    public List<E> salaryByProjectName(String projectName, Class T) {
        Session session = createSession();
        System.out.println("Open session");

        //проекты с именем
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Project> query = criteriaBuilder.createQuery(Project.class);
        Root<Project> projectRoot = query.from(Project.class);

        query.select(projectRoot)
                .where(criteriaBuilder.equal(projectRoot.get("name"), projectName))
        ;

        List<Project> resultList = session.createQuery(query).getResultList();
        Project project = resultList.get(0);
        Set<Developer> developers = project.getDevelopers();

        System.out.println(developers);


        //девелоперы с зп больше/меньше
        /*CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Developer> query = criteriaBuilder.createQuery(Developer.class);
        Root<Developer> developerRoot = query.from(Developer.class);

        query.select(developerRoot).where(criteriaBuilder.gt(developerRoot.get("salary"), 500));

        System.out.println(session.createQuery(query).getResultList());
        */

        //сумма зарплаты девелоперов - НЕ РАБОТАЕТ
        /*CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Integer> query = criteriaBuilder.createQuery(Integer.class);
        Root<Developer> developerRoot = query.from(Developer.class);

        query.select(criteriaBuilder.sum(developerRoot.get(Developer_.SALARY)));
        System.out.println(session.createQuery(query).getResultList());*/

        //Девелоперы, у которых больше двух скилов  - НЕ РАБОТАЕТ
        /*CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Developer> query = criteriaBuilder.createQuery(Developer.class);
        Root<Developer>developerRoot = query.from(Developer.class);

        Subquery<Long> subquery = query.subquery(Long.class);
        Root<Skill> skillRoot = subquery.from(Skill.class);
        SetJoin<Skill, Developer> skillSetJoin = skillRoot.join(Skill_.developers);

        subquery.select(criteriaBuilder.count(skillRoot.get(Skill_.id)));
        subquery.where(criteriaBuilder.equal(developerRoot.get(Developer_.id), skillSetJoin.get(Developer_.id)));

        query.where(criteriaBuilder.greaterThanOrEqualTo(subquery, 2L));


        System.out.println(session.createQuery(query).getResultList());*/


        //скилы у девелопера
        /*CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery query = criteriaBuilder.createQuery();
        Root<Developer>developerRoot = query.from(Developer.class);

        developerRoot.fetch(Developer_.skills, JoinType.LEFT);

        query.select(developerRoot.get("skills"))
        //.distinct(true) не повторяет
        ;

        Predicate criteria = criteriaBuilder.equal(developerRoot.get("name"), "Ivan");

        query.where(criteria);

        List<Developer> resultList = session.createQuery(query).getResultList();

        System.out.println("*****");
        System.out.println(resultList);
        System.out.println("*****");*/



        //компания у девелопера
        /*CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Company> query = criteriaBuilder.createQuery(Company.class);
        Root<Developer> developerRoot = query.from(Developer.class);

        Path<String> namePath = developerRoot.get(Developer_.name);
        Path<Company> companyPath = developerRoot.get(Developer_.company);

        *//*Join<Developer, Company> developerCompanyFetch = developerRoot.join(Developer_.company);
        developerCompanyFetch.join(Company_.name);*//*

        //query.select(developerRoot.get("company"));
        query.multiselect(namePath, companyPath);

        Predicate criteria = criteriaBuilder.equal(developerRoot.get("name"), "Ivan");

        query.where(criteria);

        List<Company> resultList = session.createQuery(query).getResultList();

        System.out.println("*****");
        System.out.println(resultList);
        System.out.println("*****");*/

        // НЕ РАБОТАЕТ
        /*CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = criteriaBuilder.createTupleQuery();

        Root<Developer> developerRoot = query.from(Developer.class);
        Join<Developer, Company> join = developerRoot.join(Company_.DEVELOPERS);
        query.multiselect(developerRoot, join);

        ParameterExpression<String> parameterExpression = criteriaBuilder.parameter(String.class);
        query.where(criteriaBuilder.equal(developerRoot.get(Company_.NAME), parameterExpression));

        Query<Tuple> q = session.createQuery(query);
        q.setParameter(parameterExpression, "EPAM");
        System.out.println(q.getResultList());*/



        /*
        "SELECT p.name, sum(d.salary) as totalSalary FROM " + databaseSchemaName + "." + "developers d" +
                " INNER JOIN developers_projects dp on d.id = dp.developer_id" +
                " INNER JOIN projects p on p.id = dp.project_id" +
                " where p.name = ?;");
        */


        System.out.println("Close session");
        closeSession(session);
        return null;
    }

    @Override
    public List<E> listDevsInProject(String projectName, Class T) {
        Session session = createSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Project> query = criteriaBuilder.createQuery(Project.class);
        Root<Project> projectRoot = query.from(Project.class);

        query.select(projectRoot)
                .where(criteriaBuilder.equal(projectRoot.get("name"), projectName))
        ;

        Project project = session.createQuery(query).getResultList().get(0);
        Set<Developer> developers = project.getDevelopers();

        System.out.println("***List Devs in a Project \"" + projectName + "\"***");
        System.out.println(developers);
        System.out.println("*****");

        /*
        "SELECT p.name as projectName, d.name as devName\n" +
                "FROM " + databaseSchemaName + "." + "developers d\n" +
                "INNER JOIN developers_projects dp on d.id = dp.developer_id\n" +
                "INNER JOIN projects p on p.id = dp.project_id\n" +
                "where p.name = ?;");
        */

        closeSession(session);
        return null;
    }

    @Override
    public List<Skill> listDevsWithSkill(String skillName, Class T) {
        Session session = createSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Developer> query = criteriaBuilder.createQuery(Developer.class);
        Root<Developer> root = query.from(Developer.class);

        Fetch<Developer, Skill> fetch1 = root.fetch(Developer_.skills);
        Fetch<Skill, String> fetch2 = fetch1.fetch(Skill_.skillsField);


        query.select(root)
                //.where(criteriaBuilder.equal(root.get("skillsField"), skillName))
        ;

        List<Developer> resultList = session.createQuery(query).getResultList();



        System.out.println("***List Devs with Skill \"" + skillName + "\"***");
        System.out.println(resultList);
        System.out.println("*****");

        /*
        "SELECT s.field as skillName, d.name as devName\n" +
                "FROM " + databaseSchemaName + "." + "developers d\n" +
                "INNER JOIN developers_skills ds on d.id = ds.developer_id\n" +
                "INNER JOIN skills s on s.id = ds.skill_id\n" +
                "where s.field = ?;");
         */

        closeSession(session);
        return null;
    }

    @Override
    public List<E> listDevsWithLevel(String levelName, Class T) {
        Session session = createSession();

        /*
        "SELECT s.level as level, d.name as devName\n" +
                "FROM " + databaseSchemaName + "." + "developers d\n" +
                "INNER JOIN developers_skills ds on d.id = ds.developer_id\n" +
                "INNER JOIN skills s on s.id = ds.skill_id\n" +
                "where s.level = ?;");
         */

        closeSession(session);
        return null;
    }

    @Override
    public List<E> listOfProjects(Class T) {
        Session session = createSession();

        /*
        "SELECT p.start, p.name, count(d.name) as quantityDevs\n" +
                "FROM " + databaseSchemaName + "." + "projects p\n" +
                "INNER JOIN developers_projects dp on p.id = dp.project_id\n" +
                "INNER JOIN developers d on d.id = dp.developer_id\n" +
                "group by p.name, p.start;");
         */

        closeSession(session);
        return null;
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

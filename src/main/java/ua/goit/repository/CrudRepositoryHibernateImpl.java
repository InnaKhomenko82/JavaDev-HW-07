package ua.goit.repository;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import ua.goit.models.BaseEntity;
import ua.goit.models.Developer;
import ua.goit.util.HibernateSessionFactory;

import javax.persistence.criteria.CriteriaQuery;
import java.io.Closeable;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CrudRepositoryHibernateImpl <ID, E extends BaseEntity<ID>> implements CrudRepository<ID, E>, Closeable {

    private final Class <E> modelClass;

    CrudRepositoryHibernateImpl(Class <E> modelClass){
        this.modelClass = modelClass;
    }

    @Override
    public List<E> findAll() {
        Session session = createSession();
        JpaCriteriaQuery<E> query = session.getCriteriaBuilder().createQuery(modelClass);
        List<E> resultList = session.createQuery(query.select(query.from(modelClass))).getResultList();
        closeSession(session);
        return resultList;
    }

    @Override
    public Optional<E> findById(ID id) {
        Session session = createSession();
        Optional<E> result = getById(id, session);
        closeSession(session);
        return result;
    }

    @Override
    public Optional<Set<E>> findById(Iterable<ID> ids) {
        return Optional.empty();
    }

    @Override
    public void deleteById(ID id) {
        Session session = createSession();
        getById(id,session).ifPresent(entity -> session.remove(entity));
        closeSession(session);
    }

    @Override
    public List<E> saveAll(Iterable<E> itrb) {
        return StreamSupport.stream(itrb.spliterator(),false)
                .map(entity -> save(entity))
                .collect(Collectors.toList());
    }

    @Override
    public E save(E e) {
        Session session = createSession();

        ID id = e.getId()==null? save(e,session): update(e,session);

        Optional<E> result = getById(id, session);
        closeSession(session);
        return result.get();
    }

    private ID save (E e, Session session){
        return (ID)session.save(e);
    }

    private ID update (E e, Session session){
        session.saveOrUpdate(e);
        return e.getId();
    }


    @Override
    public void close() {
        HibernateSessionFactory.close();
    }

    private Optional<E> getById(ID id, Session session) {
        return Optional.ofNullable(session.get(modelClass, id));
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

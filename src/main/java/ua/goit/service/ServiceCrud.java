package ua.goit.service;

import ua.goit.models.BaseEntity;
import ua.goit.repository.CrudRepository;
import ua.goit.repository.RepositoryFactory;

import java.util.List;
import java.util.Optional;

public abstract class ServiceCrud<E extends BaseEntity <ID>, ID> implements BaseService<E, ID> {


    private final CrudRepository<ID, E> REPOSITORY;

    public ServiceCrud(Class<E> classModel) {
        REPOSITORY = RepositoryFactory.of(classModel);
    }

    public E createEntity(E e){
        return REPOSITORY.save(e);
    }

    public Optional<E> findById(ID id){
        return REPOSITORY.findById(id);
    }

    public E updateEntity(E e){
        return REPOSITORY.save(e);
    }

    public void deleteById(ID id){
        REPOSITORY.deleteById(id);
    }

    public List<E> findAll(){
        return REPOSITORY.findAll();
    }
}

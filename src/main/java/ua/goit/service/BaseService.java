package ua.goit.service;

import ua.goit.models.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseService<T extends BaseEntity, ID> {
    T createEntity(T t);
    Optional<T> findById(ID id);
    T updateEntity(T t);
    void deleteById(ID id);
    List<T> findAll();
}

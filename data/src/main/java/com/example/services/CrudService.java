package com.example.services;

import java.util.Optional;
import java.util.Set;

public interface CrudService <T, ID>{
    T findById(ID id);
    Set<T> findAll();
    T save(T person);

    void delete(T object);

    void deleteById(ID id);

}

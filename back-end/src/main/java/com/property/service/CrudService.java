package com.property.service;

import java.util.List;

public interface CrudService<T,ID> {

    T save(T t);

    List<T> findAll();

    T findById(ID id);

    T update(T t, ID id);

    void deleteById(ID id);

}

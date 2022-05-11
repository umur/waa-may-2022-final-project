package waa.project.finalproj.service;

import java.util.List;

public interface CrudService<T> {
    void add(T t);
    void delete(int id);
    void update(int id, T t);
    List<T> findAll();
    T findById(int id);
}

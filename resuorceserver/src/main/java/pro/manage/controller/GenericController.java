package pro.manage.controller;

import org.springframework.web.bind.annotation.*;
import pro.manage.service.GenericService;

import java.util.List;
import java.util.Optional;

public interface GenericController <T,ID,S extends GenericService>{

    S getService();

    @GetMapping(value = {"/{id}"})
    default Optional<T> getById(@PathVariable ID id) {
        return getService().findById(id);
    }
    @PostMapping("/")
    default void add( @RequestBody T t) {
        getService().save(t);
    }

    @GetMapping("/")
    default List<T> getAll() {
        return getService().findAll();
    }

    @DeleteMapping(value ="/{id}" )
    default void deleteById(@PathVariable ID id){

        getService().deleteById(id);
    }



}

package pro.manage.service;


import org.springframework.core.GenericTypeResolver;
import org.springframework.data.repository.CrudRepository;
import pro.manage.utility.ModelMapperUti;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface GenericService<T,TO, ID,Repo  extends CrudRepository<T,ID>> {
    Repo getRepo();
    Class<TO> getDTOType();


    default List<T> findAll() {
        var result = new ArrayList<T>();
        getRepo().findAll().forEach(result::add);
      return result;

    }
    default List<TO> findAll1() {
       // Class<TO> clazz=new TO();
        var result = new ArrayList<T>();
        getRepo().findAll().forEach(result::add);
        //Type listType = new TypeToken<TO>(getClass());
        // Get the type of generic parameter

      return  ModelMapperUti.mapList(result,getDTOType());
    }
    default Class<TO> ggg(TO to)
    {
        //return     new TypeToken<TO>() {}.getType();;
        return null;
    }
    default Optional<T> findById(ID id){
        return getRepo().findById(id);
    }

    default void save(T t){
        getRepo().save(t);
    }


    default boolean existsById(ID id){
        return getRepo().findById(id).isPresent();
    };


    default List<T> findAllById(Iterable<ID> ids){
        var result = new ArrayList<T>();
        getRepo().findAllById(ids).forEach(result::add);
        return result;
    };

    default long count(){
        return getRepo().count();
    };

    default void deleteById(ID id){
        getRepo().deleteById(id);
    };

    default void delete(T entity){
        getRepo().delete(entity);
    };

    default void deleteAllById(Iterable<? extends ID> ids){
        getRepo().deleteAllById(ids);
    };

    default void deleteAll(Iterable<? extends T> entities){
        getRepo().deleteAll(entities);
    };

    default void deleteAll(){
        getRepo().deleteAll();
    };


}
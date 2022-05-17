package pro.manage.service;


import net.bytebuddy.dynamic.DynamicType;
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
    Class<T> getTType();

    default List<TO> findAll() {

        var result = new ArrayList<T>();
        getRepo().findAll().forEach(result::add);
        return  ModelMapperUti.mapList(result,getDTOType());
    }

    default Optional<TO> findById(ID id){
        Optional<T> tempObj=getRepo().findById(id);
        if(tempObj.isPresent())

        return Optional.ofNullable( ModelMapperUti.map(tempObj.get() ,getDTOType()));
        return null;
    }

    default void save(TO t){
        getRepo().save(ModelMapperUti.map(t ,getTType()));
    }


    default boolean existsById(ID id){
        return getRepo().findById(id).isPresent();
    };


    default List<TO> findAllById(Iterable<ID> ids){
        var result = new ArrayList<T>();
        getRepo().findAllById(ids).forEach(result::add);
        return    ModelMapperUti.mapList(result,getDTOType());
    };


    default long count(){
        return getRepo().count();
    };

    default void deleteById(ID id){
        getRepo().deleteById(id);
    };

    default void delete(TO entity){
        getRepo().delete(ModelMapperUti.map(entity ,getTType()));
    };

    default void deleteAllById(Iterable<? extends ID> ids){
        getRepo().deleteAllById(ids);
    };

    default void deleteAll(Iterable<? extends TO> entities){
               var result = new ArrayList<TO>();
        entities.forEach(c->result.add((TO)c));
       List<T> entitiesT= ModelMapperUti.mapList(result,getTType());
        getRepo().deleteAll(entitiesT);
    };

    default void deleteAll(){
        getRepo().deleteAll();
    };


}
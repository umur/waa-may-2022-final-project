package pro.manage.service;


import org.modelmapper.ModelMapper;
import org.springframework.data.repository.CrudRepository;
import pro.manage.utility.ModelMapperUti;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface GenericService<T,TO, ID,Repo  extends CrudRepository<T,ID>> {

    Repo getRepo();
    Class<TO> getDTOType();
    Class<T> getTType();
    final  ModelMapper modelMapper=new ModelMapper();
    default T convertDTO(TO to) {
          return modelMapper.map(to, getTType());
    }
    default List<T> convertDTO(List<TO> to){
        return   to.stream().map(c->modelMapper.map(c ,getTType())).collect(Collectors.toList());
          }
    default List<TO> convertEntity(List<T> t){
        return   t.stream().map(c->modelMapper.map(c ,getDTOType())).collect(Collectors.toList());
    }
    default TO convertEntity(T t){
        return modelMapper.map(t ,getDTOType())  ;
    }
    default List<TO> findAll() {

        var result = new ArrayList<T>();
        getRepo().findAll().forEach(result::add);
        return  convertEntity(result);
    }

    default Optional<TO> findById(ID id){
        Optional<T> tempObj=getRepo().findById(id);
        if(tempObj.isPresent())
        return Optional.ofNullable( convertEntity(tempObj.get()));
        return null;
    }

    default void save(TO t){
        getRepo().save(convertDTO(t ));
    }


    default boolean existsById(ID id){
        return getRepo().findById(id).isPresent();
    };


    default List<TO> findAllById(Iterable<ID> ids){
        var result = new ArrayList<T>();
        getRepo().findAllById(ids).forEach(result::add);
        return    convertEntity(result);
    };


    default long count(){
        return getRepo().count();
    };

    default void deleteById(ID id){
        getRepo().deleteById(id);
    };

    default void delete(TO entity){
        getRepo().delete(convertDTO(entity));
    };

    default void deleteAllById(Iterable<? extends ID> ids){
        getRepo().deleteAllById(ids);
    };

    default void deleteAll(Iterable<? extends TO> entities){
               var result = new ArrayList<TO>();
        entities.forEach(c->result.add((TO)c));
       List<T> entitiesT= convertDTO(result);
        getRepo().deleteAll(entitiesT);
    };

    default void deleteAll(){
        getRepo().deleteAll();
    };


}
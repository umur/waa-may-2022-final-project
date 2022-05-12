package waa.propertymanagementbackend.service;

import java.util.List;

public interface CrudService <TDto> {

    void save(TDto dto);

    List<TDto> getAll();

    TDto getById(int id);

    void delete(int id,boolean value);

}




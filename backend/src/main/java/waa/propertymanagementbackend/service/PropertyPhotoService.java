package waa.propertymanagementbackend.service;


import waa.propertymanagementbackend.domain.PropertyPhotos;
import waa.propertymanagementbackend.domain.User;

import java.util.List;

public interface PropertyPhotoService<TDto> {

    public void save(TDto p);
    void saveImageUrl(String [] imageUrls);

    List<TDto> getAll();

    TDto getById(int id);

    void delete(int id,boolean value);






}

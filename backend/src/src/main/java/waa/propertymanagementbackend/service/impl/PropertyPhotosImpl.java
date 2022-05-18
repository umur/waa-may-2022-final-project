package waa.propertymanagementbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import waa.propertymanagementbackend.domain.PropertyPhotos;
import waa.propertymanagementbackend.repository.PropertyPhotosRep;
import waa.propertymanagementbackend.repository.UserRepository;
import waa.propertymanagementbackend.service.CrudService;
import waa.propertymanagementbackend.service.PropertyPhotoService;

import java.util.List;

public class PropertyPhotosImpl implements PropertyPhotoService<PropertyPhotos> {
    @Autowired
    PropertyPhotosRep propertyPhotosRep;

    @Override
    public void saveImageUrl(String [] imageUrls) {
        PropertyPhotos propertyPhoto=new PropertyPhotos();
        for (int i = 0; i <imageUrls.length ; i++) {
            propertyPhoto.setId(propertyPhotosRep.getLastId()+1);
            propertyPhoto.setPhotoUrl(imageUrls[i]);
            propertyPhotosRep.save(propertyPhoto);
        }

    }
    @Override
    public void save(PropertyPhotos p) {
       // PropertyPhotos propertyPhoto=new PropertyPhotos();
      //  propertyPhoto= propertyPhotosRep.findByPhotoUrl(p.getPhotoUrl(),int propertyId);
          //  propertyPhoto.setId(propertyPhotosRep.getLastId()+1);
           // propertyPhoto.setPhotoUrl(p.getPhotoUrl());
            propertyPhotosRep.save(p);


    }
    @Override
    public List<PropertyPhotos> getAll() {
        return (List<PropertyPhotos>) propertyPhotosRep.findAll();
    }

    @Override
    public PropertyPhotos getById(int id) {
        return  propertyPhotosRep.findById(id).get();
    }

    @Override
    public void delete(int id, boolean value) {

    }
}

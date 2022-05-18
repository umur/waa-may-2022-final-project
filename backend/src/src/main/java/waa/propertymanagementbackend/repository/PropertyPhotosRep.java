package waa.propertymanagementbackend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import waa.propertymanagementbackend.domain.PropertyPhotos;

@Repository
public interface PropertyPhotosRep extends CrudRepository<PropertyPhotos, Integer> {
    @Query(value =
            "select max(id) from property_photos",
            nativeQuery = true)
    public int getLastId();
//PropertyPhotos findByPhotoUrlA(String url,int propertyId);
}

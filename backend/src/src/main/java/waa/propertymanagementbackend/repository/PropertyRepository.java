package waa.propertymanagementbackend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import waa.propertymanagementbackend.domain.Property;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PropertyRepository extends CrudRepository<Property, Integer> {
    public List<Property> findByOwnedByEmail(String email);


    public List<Property> findByAddressCity(String city);

    public List<Property> findByLastRentedByEmail(String email);

    public List<Property> findByOwnedByEmailAndAddressCity(String email, String city);

    public List<Property> findByOwnedByEmailAndAddressCityAndNumberOfBedrooms(String email, String city, int numberOfBedrooms);

    public List<Property> findByOwnedByEmailAndNumberOfBedrooms(String email, int numberOfBedrooms);

    public List<Property> findByOwnedByEmailAndNumberOfBedroomsAndIsOccupied(String email, int numberOfBedrooms, boolean isOccupied);

    public List<Property> findByNumberOfBedroomsAndIsOccupiedAndVisible(int numberOfBedrooms, boolean isOccupied, boolean isVisible);

    public List<Property> findByAddressCityAndNumberOfBedroomsAndIsOccupiedAndVisible(String city, int numberOfBedrooms, boolean isOccupied, boolean isVisible);

    public List<Property> findByOwnedByEmailAndIsOccupied(String city, boolean isOccupied);

    public List<Property> findByAddressCityAndIsOccupiedAndVisible(String city, boolean isOccupied, boolean isVisible);
    //public  List<Property> findByOwnedByEmailAndRentedToIsLessThanEqualAndIsOccupied(String email, LocalDate date,boolean isOccupied);

    @Query(value =
            "select max(id) from property",
            nativeQuery = true)
    public int getLastId();

}

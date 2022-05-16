package waa.propertymanagementbackend.repository;

import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import waa.propertymanagementbackend.domain.PropertyRentHistory;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PropertyRentHistoryRepo extends CrudRepository<PropertyRentHistory, Integer> {
    List<PropertyRentHistory> findByPropertyId(int id);

    //PropertyRentHistory findByPropertyIdAndRentedToLessThan(int id, LocalDate date);
    @Query(value = "\n" +
            "select *  from property_rent_history h,property p ,users u\n" +
            "where h.property_id=p.id  and p.owned_by=u.id\n" +
            "  and u.email=:email\n" +
            "and h.active=true and  h.rented_to<:date",
            nativeQuery = true)
    List<PropertyRentHistory> getPropertiesLeasesInMonth(String email, LocalDate date);

    @Query(value = "select * from property_rent_history where  active=true order by rented_from desc limit 10",
            nativeQuery = true)
    List<PropertyRentHistory> findByActiveOrderByRentedToAsc(boolean isActive);

    @Query(value = "\n" +
            "select  sum(rented_amount)  from property_rent_history h,property p ,address d\n" +
            "where h.property_id=p.id  and p.address_id=d.id and d.city=:city",
            nativeQuery = true)
    float getTotalIncomePerLocation(String city);

    @Query(value = "\n" +
            "select  sum(rented_amount)  from property_rent_history h,property p ,address d,users u\n" +
            "     where h.property_id=p.id  and p.address_id=d.id and d.city=:city\n" +
            "and  p.owned_by=u.id  and u.email=:email",
            nativeQuery = true)
    float getTotalIncomePerLocationAndLandLord(String city, String email);

    @Query(value =
            "select max(id) from property",
            nativeQuery = true)
    public int getLastId();

}


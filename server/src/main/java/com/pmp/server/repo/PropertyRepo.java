package com.pmp.server.repo;

import com.joutvhu.dynamic.jpa.DynamicQuery;
import com.pmp.server.domain.Property;
import com.pmp.server.domain.User;
import com.pmp.server.dto.PropertyIncomeDTO;
import com.pmp.server.dto.common.PagingRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PropertyRepo extends PagingAndSortingRepository<Property, UUID> {
    Page<Property> findAllByCityIsLikeIgnoreCaseOrStateIsLikeIgnoreCaseAndNumberOfBedroomsGreaterThanEqualAndActiveIsTrueAndOwnedByActiveIsTrue(Pageable page, String loc,String loc2, int room);

    Page<Property> findByLastRentedDateNotNull(Pageable page);
    Page<Property> findAllByOwnedBy(Pageable page, User u);

    Page<Property> findAllByActiveIsTrue(Pageable page);

    Page<Property> findAllByActiveIsTrueAndOwnedByActiveIsTrue(Pageable page);

    @Query(value = "select * from properties p where p.owned_by_id =?1", nativeQuery = true)
    List<Property> customFindByOwner(UUID id);

    @Query(value = "SELECT *\n" +
      "FROM Properties p\n" +
      "WHERE (lower(p.property_name) LIKE ?1\n" +
      "   or lower(p.state) LIKE ?1\n" +
      "   or lower(p.property_type) LIKE ?1\n" +
      "   or lower(p.city) LIKE ?1\n" +
      "   or lower(p.description) LIKE ?1\n" +
      "   or lower(p.property_type) LIKE ?1\n" +
      "   or lower(p.street_address) LIKE ?1)\n" +
      "AND p.no_of_bedroom >= ?3\n" +
      "AND p.owned_by_id=?2 \n" +
      "AND p.is_deleted = false",
            nativeQuery = true)
    Page<Property> customSearch(Pageable page, String s, UUID id, int rooms);

//    @Query(value = "select\n" +
//      "        p.id,\n" +
//      "        p.property_name,\n" +
//      "        p.street_address,\n" +
//      "        p.state,\n" +
//      "        sum(prh.transaction_amount) as transaction_amount\n" +
//      "    from\n" +
//      "        properties p\n" +
//      "    inner join\n" +
//      "        property_rental_history prh\n" +
//      "            on p.id = prh.property_id\n" +
//      "    inner join\n" +
//      "        transactions t\n" +
//      "            on prh.id = t.property_rental_history_id\n" +
//      "    group by\n" +
//      "        p.id",
//      nativeQuery = true)
//     List<PropertyIncomeDTO> propertyIncome();

}

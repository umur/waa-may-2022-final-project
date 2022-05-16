package com.pmp.server.repo;

import com.pmp.server.domain.Property;
import com.pmp.server.domain.User;
import com.pmp.server.dto.PropertyIncomeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PropertyRepo extends PagingAndSortingRepository<Property, UUID> {
    Page<Property> findAllByCityIsLikeIgnoreCaseAndAndNumberOfBedroomsGreaterThanEqualAndActiveIsTrue(Pageable page, String loc, int room);
    Page<Property> findAllByOwnedBy(Pageable page, User u);

    @Query(value = "SELECT * FROM Properties p WHERE lower(p.property_name) LIKE ?1 or lower(p.state) LIKE ?1 or lower(p.property_type) LIKE ?1 or lower(p.city) LIKE ?1 or lower(p.description) LIKE ?1 or lower(p.property_type) LIKE ?1 or lower(p.street_address) LIKE ?1  ",
            nativeQuery = true)
    Page<Property> customSearch(Pageable page,String s);

    @Query(value = "select\n" +
      "p.id, p.property_name, p.street_address, p.state, sum(prh.transaction_amount) as transaction_amount\n" +
      "from properties p\n" +
      "    inner join property_rental_history prh on p.id = prh.property_id\n" +
      "    inner join transactions t on prh.id = t.property_rental_history_id\n" +
      "group by p.id) propertyincome\n" +
      "order by propertyincome.transaction_amount desc ",
      nativeQuery = true)
    Page<PropertyIncomeDTO> propertyIncome(Pageable p);

}

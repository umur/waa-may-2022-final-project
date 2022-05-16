package waa.propertymanagementbackend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import waa.propertymanagementbackend.domain.EmailDataDetail;

@Repository
public interface EmailDataDetailRep extends CrudRepository<EmailDataDetail,Integer> {
    @Query(value =
            "select max(id) from email_messages",
            nativeQuery = true)
    public int getLastId();
}

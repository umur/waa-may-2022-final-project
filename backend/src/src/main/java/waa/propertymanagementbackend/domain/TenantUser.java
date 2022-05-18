package waa.propertymanagementbackend.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;


//@Data
//@Entity
public class TenantUser  extends User {
  //  @Id
    private int id;

    //@OneToMany
       //     @JoinColumn()
    List<Property> propertyList;
}

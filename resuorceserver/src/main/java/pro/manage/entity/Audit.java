package pro.manage.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;


}

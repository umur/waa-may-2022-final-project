package pro.manage.service;
import pro.manage.entity.Address;
import pro.manage.entity.Property;
import pro.manage.entity.dto.PropertyDto;
import pro.manage.repository.PropertyRepository;

public interface PropertyService extends GenericService<Property, PropertyDto,Long, PropertyRepository> {


   public Property findByAddress(Address address);
}

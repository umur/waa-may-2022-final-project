package pro.manage.service;

import pro.manage.entity.Address;
import pro.manage.entity.Property;
import pro.manage.entity.dto.PropertyDto;
import pro.manage.repository.PropertyRepository;
import pro.manage.utility.ModelMapperUti;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public interface PropertyService extends GenericService<Property, PropertyDto, UUID, PropertyRepository> {

   default List<PropertyDto> convertEntity(List<Property> t){
      //modelMapper.typeMap(Property.class, PropertyDto.class).addMapping(Property::getPhotos, PropertyDto::setImages);
//      try {
//         modelMapper.typeMap(Property.class, PropertyDto.class)
//                 .addMapping(src -> src.getPhotos().stream().map(cc -> cc.getPath()).collect(Collectors.toList()), PropertyDto::setImages);
//      }catch (Error e){
//
//      }
      return   t.stream().map(c->convertEntity(c )).collect(Collectors.toList());

   }
   default PropertyDto convertEntity(Property t){
//      modelMapper.typeMap(Property.class, PropertyDto.class)
//              .addMapping(src -> src.getPhotos().stream().map(cc -> cc.getPath()).collect(Collectors.toList()), PropertyDto::setImages);
      PropertyDto dto= modelMapper.map(t ,getDTOType())  ;
      dto.setImages(t.getPhotos().stream().map(cc -> cc.getPath()).collect(Collectors.toList()));
      return dto;
   }
   public Property findByAddress(Address address);
}

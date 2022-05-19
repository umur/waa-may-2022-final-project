
package pro.manage.controller;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.manage.entity.Property;
import pro.manage.entity.dto.PropertyDto;
import pro.manage.service.PropertyService;

import java.util.UUID;


@RestController
@CrossOrigin(allowedHeaders = "*")
@RequestMapping(value="/properties")
@AllArgsConstructor
public class PropertyController implements GenericController<PropertyDto, UUID, PropertyService>{


    private final PropertyService propertyService;

    @Override
    public PropertyService getService() {
        return propertyService;
    }






    /*
    @GetMapping(value = "/search/{state}/{city}")
    public List<House> getHouseByStateAndCity(@PathVariable String state, @PathVariable String city) {

        List<Address>  address = addressService.findAllByStateAndCity(state, city);
        List<House> houses = new ArrayList<>();

        for (Address a : address) {
            System.out.println("getting houses");
            House h = houseService.findByAddress(a);
            if(h!=null)
                houses.add(h);
        }
//        for (House hx:houses){
//            System.out.println(hx.getHouseType());
//        }

        House [] filteredHouses= new House[houses.size()];
         houses.toArray(filteredHouses);

        return  filteredHouses;
    }

     */



    //    @PutMapping(value = "/edit/{houseId}")
//    public House editHouse(@Valid @RequestBody House editedHouse, @PathVariable Long houseId) {
//        return houseService.updateHouse(editedHouse, houseId);
//
//    }


}



package waa.project.finalproj.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waa.project.finalproj.dto.propertyType.PropertyTypeDTO;
import waa.project.finalproj.service.PropertyTypeService;

@RestController
@RequestMapping("/api/v1/property-type")
@CrossOrigin
@RequiredArgsConstructor
public class PropertyTypeController {

    private final PropertyTypeService propertyTypeService;

    @PostMapping
    public void save(@RequestBody PropertyTypeDTO h){
        propertyTypeService.add(h);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(propertyTypeService.findAllWhereDeletedAtNotNull());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok().body(propertyTypeService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        propertyTypeService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody PropertyTypeDTO l){
        propertyTypeService.update(id, l);
    }

}

package waa.project.finalproj.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waa.project.finalproj.dto.property.PropertyDTO;
import waa.project.finalproj.dto.property.PropertySaveDTO;
import waa.project.finalproj.service.PropertyService;

@RestController
@RequestMapping("/api/v1/properties")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping
    public void save(@RequestBody PropertySaveDTO h){
        propertyService.add(h);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(propertyService.findAllWhereDeletedAtNotNull());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok().body(propertyService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        propertyService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody PropertyDTO l){
        propertyService.update(id, l);
    }

    @GetMapping("/last-ten-rented")
    public ResponseEntity<?> findLast10Rented() {
        return ResponseEntity.ok().body(propertyService.findTop10OrderByIdDesc());
    }

}

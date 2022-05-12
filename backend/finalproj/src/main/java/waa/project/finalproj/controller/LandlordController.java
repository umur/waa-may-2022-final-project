package waa.project.finalproj.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waa.project.finalproj.dto.landlord.LandlordDTO;
import waa.project.finalproj.dto.landlord.LandlordSaveDTO;
import waa.project.finalproj.service.LandlordService;

@RestController
@RequestMapping("/api/v1/landlords")
@CrossOrigin
@RequiredArgsConstructor
public class LandlordController {

    private final LandlordService landlordService;

    @PostMapping
    public void save(@RequestBody LandlordSaveDTO l){
        landlordService.add(l);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(landlordService.findAllWhereDeletedAtNotNull());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok().body(landlordService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        landlordService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody LandlordDTO l){
        landlordService.update(id, l);
    }

}

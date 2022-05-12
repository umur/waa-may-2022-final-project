package waa.project.finalproj.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waa.project.finalproj.dto.house.HouseDTO;
import waa.project.finalproj.dto.house.HouseSaveDTO;
import waa.project.finalproj.service.HouseService;

@RestController
@RequestMapping("/api/v1/houses")
@CrossOrigin
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;

    @PostMapping
    public void save(@RequestBody HouseSaveDTO h){
        houseService.add(h);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(houseService.findAllWhereDeletedAtNotNull());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok().body(houseService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        houseService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody HouseDTO l){
        houseService.update(id, l);
    }

}

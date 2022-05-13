package waa.project.finalproj.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waa.project.finalproj.dto.Rent.RentDTO;
import waa.project.finalproj.dto.Rent.RentSaveDTO;
import waa.project.finalproj.service.RentService;

@RestController
@RequestMapping("/api/v1/rents")
@CrossOrigin
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;

    @PostMapping
    public void save(@RequestBody RentSaveDTO h){
        rentService.add(h);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(rentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok().body(rentService.findById(id));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody RentDTO l){
        rentService.update(id, l);
    }

}

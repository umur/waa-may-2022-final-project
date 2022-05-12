package waa.project.finalproj.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waa.project.finalproj.dto.admin.AdminDTO;
import waa.project.finalproj.dto.admin.AdminSaveDTO;
import waa.project.finalproj.service.AdminService;

@RestController
@RequestMapping("/api/v1/admins")
@CrossOrigin
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public void save(@RequestBody AdminSaveDTO adminDTO){
        adminService.add(adminDTO);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(adminService.findAllWhereDeletedAtNotNull());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok().body(adminService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        adminService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody AdminDTO adminDTO){
        adminService.update(id, adminDTO);
    }
}

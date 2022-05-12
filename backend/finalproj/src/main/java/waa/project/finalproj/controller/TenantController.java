package waa.project.finalproj.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waa.project.finalproj.dto.tenant.TenantDTO;
import waa.project.finalproj.dto.tenant.TenantSaveDTO;
import waa.project.finalproj.service.TenantService;

@RestController
@RequestMapping("/api/v1/tenants")
@CrossOrigin
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    @PostMapping
    public void save(@RequestBody TenantSaveDTO l){
        tenantService.add(l);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(tenantService.findAllWhereDeletedAtNotNull());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok().body(tenantService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        tenantService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody TenantDTO l){
        tenantService.update(id, l);
    }

}

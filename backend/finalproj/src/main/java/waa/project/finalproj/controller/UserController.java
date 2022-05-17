package waa.project.finalproj.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waa.project.finalproj.dto.user.UserDTO;
import waa.project.finalproj.dto.user.UserSaveDTO;
import waa.project.finalproj.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody UserSaveDTO h) throws Exception {
        if ("ADMIN".equals(h.getRole())) return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        userService.add(h);
        return ResponseEntity.ok().body("");
    }

    @PostMapping("/admin")
    public void saveAdmin(@RequestBody UserSaveDTO h) throws Exception {
        userService.add(h);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(userService.findAllWhereDeletedAtNotNull());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody UserDTO l){
        userService.update(id, l);
    }

    @GetMapping("/get-by-role")
    public ResponseEntity<?> findAllRole(@RequestParam String role) {
        return ResponseEntity.ok().body(userService.findAllByRoleAndDeletedAtIsNullOrderByIdDesc(role));
    }

}

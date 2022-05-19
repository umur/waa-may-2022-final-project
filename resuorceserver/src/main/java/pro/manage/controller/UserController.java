package pro.manage.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.manage.entity.dto.UserDto;
import pro.manage.entity.dto.UserSaveDTO;
import pro.manage.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController implements GenericController<UserDto, UUID, UserService> {


    private final UserService userService;

    @Override
    public UserService getService() {
        return userService;
    }

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


    @GetMapping("/get-by-role")
    public ResponseEntity<?> findAllRole(@RequestParam String role) {
        return ResponseEntity.ok().body(userService.findAllByRoleAndDeletedAtIsNullOrderByIdDesc(role));
    }

}

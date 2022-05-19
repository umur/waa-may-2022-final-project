package waa.propertymanagementbackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waa.propertymanagementbackend.domain.User;
import waa.propertymanagementbackend.dto.EmailDataDetailDto;
import waa.propertymanagementbackend.dto.UserDto;
import waa.propertymanagementbackend.dto.UserSignupDto;
import waa.propertymanagementbackend.service.EmailService;
import waa.propertymanagementbackend.service.UserService;


import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UaaController {
    @Autowired
    private UserService<UserDto> userService;

    @Autowired
    private EmailService<EmailDataDetailDto> emailService;

    /**
     * ADmin
     */
    @GetMapping("/admin/recentUsers")
    public List<UserDto> getLastRecentTenants() {
        return userService.getLastRecentTenants();
    }


    @GetMapping("/dto/{id}")
    public UserDto getById(@PathVariable int id) {
        return userService.getById(id);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }
    @GetMapping("/byEmail/{email}")
    public UserDto getUserByEmail(@PathVariable String email) {
        return userService.getUserDtoByEmail(email);
    }

    @GetMapping("/admin/byRole/{roleName}")
    public List<UserDto> getUsersByRole(@PathVariable String roleName) {
        return userService.getUserDtoByRole(roleName);
    }

    @PutMapping("/admin/activation/{email}/{value}")
    public void activate(@PathVariable String email, @PathVariable boolean value) {
        userService.activate(email, value);
    }


    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody EmailDataDetailDto dto) {
        return emailService.sendEmail(dto);
    }

    @GetMapping("/emails")
    public List<EmailDataDetailDto> getEmails() {
        return emailService.getAll();
    }


    @GetMapping("/emails/{id}")
    public EmailDataDetailDto getEmails(@PathVariable int id) {
        return emailService.getById(id);
    }




    @PostMapping("/signup")
    public void signUp(@RequestBody UserSignupDto user) {
        System.out.println("Helllllo");
        userService.save(user);

    }

    ////////////////////
/*
    @Autowired
    private UserServiceImpl uaaService;




@PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        var loginResponse = uaaService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }


   @PostMapping("/signup")
   public ResponseEntity<?> signUp(@RequestBody User user) {
       System.out.println("Helllllo");
        uaaService.signUp(user);
       return ResponseEntity.ok().body("fgdfgfgfg");
    }





    @GetMapping
    public List<UsersDto> getALL() {
        return uaaService.getAll();
    }

    @PostMapping("/refreshToken")
    public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return uaaService.refreshToken(refreshTokenRequest);
    }
*/


}

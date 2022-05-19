package pro.manage.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.manage.entity.dto.UserAttrOnlyDTO;
import pro.manage.model.ForgotPassword;
import pro.manage.model.PasswordReset;
import pro.manage.service.EmailService;
import pro.manage.service.UserService;


@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1/password")
public class ForgotPasswordController {

    private final UserService userService;
    private EmailService emailService;
    private final ModelMapper modelMapper;

    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestBody ForgotPassword forgotPassword) throws Exception {

        String response = userService.forgotPassword(forgotPassword.getEmail());
        if (!response.startsWith("Invalid")) {
            response = forgotPassword.getLink() + "?token=" + response;
            emailService.sendSimpleMessage(forgotPassword.getEmail(), "Reset Password", response);
        }
    }


    @PutMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordReset passwordReset) throws Exception {
        if (passwordReset.getPassword().length() > 3){
            var r = userService.resetPassword(passwordReset.getToken(), passwordReset.getPassword());
            return r != null ? ResponseEntity.ok().body(modelMapper.map(r, UserAttrOnlyDTO.class)) : ResponseEntity.badRequest().body("Bad Request");
        }
        return ResponseEntity.badRequest().body("Bad Request");
    }

}

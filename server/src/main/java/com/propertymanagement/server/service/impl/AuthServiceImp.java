package com.propertymanagement.server.service.impl;

import com.propertymanagement.server.domain.PasswordDto;
import com.propertymanagement.server.domain.PasswordResetToken;
import com.propertymanagement.server.domain.User;
import com.propertymanagement.server.dto.LoginDto;
import com.propertymanagement.server.dto.LoginRespDto;
import com.propertymanagement.server.dto.UserRespDto;
import com.propertymanagement.server.exception.MailIsNotSendException;
import com.propertymanagement.server.exception.UserNotFoundException;
import com.propertymanagement.server.repository.PasswordResetTokenRepository;
import com.propertymanagement.server.repository.UserRepository;
import com.propertymanagement.server.security.JwtTokenUtil;
import com.propertymanagement.server.security.PasswordUtil;
import com.propertymanagement.server.service.AuthService;
import com.propertymanagement.server.util.EmailSender;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.passay.PasswordUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final PasswordUtil passwordUtil;

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    private final EmailSender emailSender;

    @Override
    public LoginRespDto login(LoginDto loginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw new UserNotFoundException("Username or password is incorrect");
        }
        final String accessToken = jwtTokenUtil.generateToken(loginDto.getEmail());

        var loginResponse = new LoginRespDto(accessToken);
        return loginResponse;
    }

    @Override
    public LoginRespDto logins(LoginDto loginDto) {
        User user = null;
        try {
            user = userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        } catch (BadCredentialsException e) {
            throw new UserNotFoundException("Username or password is incorrect");
        }

        String accessToken = "error";
        if(user != null) {
            accessToken = "";
        }
        var loginResponse = new LoginRespDto(accessToken);
        return loginResponse;
    }

    @Override
    public UserRespDto resetPassword(PasswordDto passwordDto, String token) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        User user = passwordResetToken.getUser();
        user.setPassword(passwordEncoder.encode(passwordDto.getPassword()));
        userRepository.save(user);
        return modelMapper.map(user, UserRespDto.class);
    }

    public void sendForgotPasswordEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User is not found");
        }
        try {
            String token = UUID.randomUUID().toString();
            PasswordResetToken passwordResetToken = new PasswordResetToken();
            passwordResetToken.setUser(user);
            passwordResetToken.setToken(token);
            passwordResetToken.setExpiryDateTime(LocalDateTime.now().plusHours(1));
            passwordResetTokenRepository.save(passwordResetToken);
            String resetPasswordLink = "http://localhost:3000" + "/reset-password?token=" + token;
            emailSender.sendEmail(email, resetPasswordLink);

        } catch (UnsupportedEncodingException | MessagingException e) {
            throw new MailIsNotSendException("Could not send email");
        }
    }
}

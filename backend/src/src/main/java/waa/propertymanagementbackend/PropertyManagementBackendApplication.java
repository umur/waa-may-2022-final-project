package waa.propertymanagementbackend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
public class PropertyManagementBackendApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
//    @Bean
//    public JavaMailSenderImpl mailSender() {
//        return new JavaMailSenderImpl();
//    }
    public static void main(String[] args) {
        SpringApplication.run(PropertyManagementBackendApplication.class, args);
    }

}

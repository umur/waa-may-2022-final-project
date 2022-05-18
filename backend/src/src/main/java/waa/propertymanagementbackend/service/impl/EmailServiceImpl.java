package waa.propertymanagementbackend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import waa.propertymanagementbackend.domain.EmailDataDetail;
import waa.propertymanagementbackend.dto.EmailDataDetailDto;
import waa.propertymanagementbackend.repository.EmailDataDetailRep;
import waa.propertymanagementbackend.repository.UserRepository;
import waa.propertymanagementbackend.service.EmailService;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmailServiceImpl implements EmailService<EmailDataDetailDto> {
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailDataDetailRep emailDataDetailRep;
    @Override
    public List<EmailDataDetailDto> getAll() {
        List<EmailDataDetail> emailDataDetails = (List<EmailDataDetail>) emailDataDetailRep.findAll();

        List<EmailDataDetailDto> dtos = new ArrayList<>();
        EmailDataDetailDto dto = new EmailDataDetailDto();
        emailDataDetails.stream().forEach(item -> {
            modelMapper.map(item, dto);
            dtos.add(dto);
        });

        return dtos;
    }

    @Override
    public EmailDataDetailDto getById(int id) {
        EmailDataDetailDto dto = new EmailDataDetailDto();
        modelMapper.map(emailDataDetailRep.findById(id).get(), dto);
        return dto;

    }


    /**
     * Send Emails
     */
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public String sendEmail(EmailDataDetailDto dto) {
        try {
            System.out.println("helllo");;
            EmailDataDetail em = new EmailDataDetail();
            modelMapper.map(dto, em);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("waaprogectresetpass@gmail.com");
            message.setTo(dto.getReceiver().getEmail());
            message.setSubject(dto.getSubject());
            message.setText(dto.getMessageBody());
            javaMailSender.send(message);


            em.setId(emailDataDetailRep.getLastId()+1);
            emailDataDetailRep.save(em);

            return "Mail sent successfully";
        }
        catch (Exception e){
            return "Error Please try Again Later";
        }

    }
}

package waa.propertymanagementbackend.service;

import waa.propertymanagementbackend.dto.EmailDataDetailDto;

import java.util.List;

public interface EmailService<TDto> {
    public List<TDto> getAll();
    TDto getById(int id);
    public String sendEmail(EmailDataDetailDto dto);
}

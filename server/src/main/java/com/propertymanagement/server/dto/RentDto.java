package com.propertymanagement.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentDto {
    private LocalDate startDate;
    private LocalDate endDate;
}
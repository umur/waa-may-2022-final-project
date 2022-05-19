package com.project.accessdenied.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TenantRentDto {

    private long id;
    private long pid;
    private LocalDate endDate;
}

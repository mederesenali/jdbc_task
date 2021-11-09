package com.example.task.model;

import lombok.Data;

@Data
public class ExportDto {
    private int companyId;
    private String companyName;
    private String jobTitle;
    private String candidateLastName;
}

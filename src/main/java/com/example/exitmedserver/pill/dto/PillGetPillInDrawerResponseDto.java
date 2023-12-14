package com.example.exitmedserver.pill.dto;

import lombok.Data;

import java.util.List;

@Data
public class PillGetPillInDrawerResponseDto {
    private String pillName;
    private String imageLink;
    private List<String> alarm;
    private String finalDate;
    private String dosage;
    private String comment;
}

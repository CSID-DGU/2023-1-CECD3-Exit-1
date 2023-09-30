package com.example.exitmedserver.pill.dto;

import lombok.Data;

@Data
public class PillGetPillInDrawerResponseDto {
    private String pillName;
    private String imageLink;
    private String alarm;
    private String finalDate;
    private String dosage;
    private String comment;
}

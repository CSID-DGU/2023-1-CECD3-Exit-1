package com.example.exitmedserver.pill.dto;

import lombok.Data;

@Data
public class PillAddDrawerRequestDto {
    private Long pillItemSequence;
    private String finalDate;
    private String dosageCycle;
    private String comment;
    private String takeTime;
}

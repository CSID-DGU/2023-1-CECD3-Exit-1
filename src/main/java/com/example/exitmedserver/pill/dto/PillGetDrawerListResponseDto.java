package com.example.exitmedserver.pill.dto;

import lombok.Data;

@Data
public class PillGetDrawerListResponseDto {
    private Long pillItemSequence;
    private String pillName;
    private String classification;
    private String imageLink;
    private boolean isAlarmTurnedOn;
}

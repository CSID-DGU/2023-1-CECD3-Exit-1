package com.example.exitmedserver.pill.dto;

import lombok.Data;

@Data
public class PillGetAlarmListResponseDto {
    private String pillName;
    private String takeTime;
    private boolean isPassed;
}

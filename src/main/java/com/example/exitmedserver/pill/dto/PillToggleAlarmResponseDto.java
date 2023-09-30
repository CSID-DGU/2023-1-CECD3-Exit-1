package com.example.exitmedserver.pill.dto;

import lombok.Data;

@Data
public class PillToggleAlarmResponseDto {
    private boolean isOn;
    private Long pillItemSequence;
}

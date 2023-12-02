package com.example.exitmedserver.pill.dto;

import lombok.Data;

import java.util.List;

@Data
public class PillGetMaxAllowedResponse {
    private List<PillGetMaxAllowedResponseDto> data;
}

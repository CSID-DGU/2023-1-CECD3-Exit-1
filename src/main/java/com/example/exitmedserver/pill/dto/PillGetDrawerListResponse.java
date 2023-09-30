package com.example.exitmedserver.pill.dto;

import lombok.Data;

import java.util.List;

@Data
public class PillGetDrawerListResponse {
    private List<PillGetDrawerListResponseDto> data;
}

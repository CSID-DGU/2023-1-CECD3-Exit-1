package com.example.exitmedserver.user.dto;

import com.example.exitmedserver.search.dto.SearchTextResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class SearchTextResponse {
    private List<SearchTextResponseDto> data;
}

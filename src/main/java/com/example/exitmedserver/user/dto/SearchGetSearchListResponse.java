package com.example.exitmedserver.user.dto;

import com.example.exitmedserver.search.dto.SearchGetSearchListResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class SearchGetSearchListResponse {
    private List<SearchGetSearchListResponseDto> data;
}

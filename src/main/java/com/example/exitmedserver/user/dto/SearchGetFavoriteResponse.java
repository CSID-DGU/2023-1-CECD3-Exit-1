package com.example.exitmedserver.user.dto;

import com.example.exitmedserver.search.dto.SearchGetFavoriteResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class SearchGetFavoriteResponse {
    private List<SearchGetFavoriteResponseDto> data;
}

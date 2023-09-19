package com.example.exitmedserver.search.controller;

import com.example.exitmedserver.search.dto.SearchAddFavoriteRequestDto;
import com.example.exitmedserver.search.dto.SearchGetFavoriteResponseDto;
import com.example.exitmedserver.search.dto.SearchTextResponseDto;
import com.example.exitmedserver.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;
    @GetMapping("/auth/search/text-search/{searchText}")
    public List<SearchTextResponseDto> textSearch(@PathVariable String searchText) {
        List<SearchTextResponseDto> searchTextResponse = searchService.searchText(searchText);
        System.out.println(searchTextResponse);
        return searchTextResponse;
    }

    @PostMapping("/auth/search/favorite")
    public boolean addToFavorite(@RequestHeader("Authorization") String jwtToken, @RequestBody SearchAddFavoriteRequestDto searchAddFavoriteRequestDto) {
        return searchService.addToFavorite(jwtToken, searchAddFavoriteRequestDto.getPillItemSequence());
    }

    @GetMapping("/auth/search/favorite")
    public List<SearchGetFavoriteResponseDto> getFavorite(@RequestHeader("Authorization") String jwtToken) {
        searchService.getFavorite(jwtToken);
        return searchService.getFavorite(jwtToken);
    }
}

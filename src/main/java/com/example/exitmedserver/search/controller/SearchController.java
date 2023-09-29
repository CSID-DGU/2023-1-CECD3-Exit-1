package com.example.exitmedserver.search.controller;

import com.example.exitmedserver.search.dto.SearchAddFavoriteRequestDto;
import com.example.exitmedserver.search.dto.SearchAddFavoriteResponseDto;
import com.example.exitmedserver.search.service.SearchService;
import com.example.exitmedserver.user.dto.SearchGetFavoriteResponse;
import com.example.exitmedserver.user.dto.SearchGetSearchListResponse;
import com.example.exitmedserver.user.dto.SearchTextResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;
    @GetMapping("/auth/search/text-search/{searchText}")
    public SearchTextResponse textSearch(@RequestHeader("Authorization") String jwtToken, @PathVariable String searchText) {
        searchService.addToSearchHistory(jwtToken, searchText);
        return searchService.searchText(searchText);
    }

    @PostMapping("/auth/search/favorite")
    public SearchAddFavoriteResponseDto addToFavorite(@RequestHeader("Authorization") String jwtToken, @RequestBody SearchAddFavoriteRequestDto searchAddFavoriteRequestDto) {
        return searchService.addToFavorite(jwtToken, searchAddFavoriteRequestDto.getPillItemSequence());
    }

    @GetMapping("/auth/search/favorite")
    public SearchGetFavoriteResponse getFavorite(@RequestHeader("Authorization") String jwtToken) {
        return searchService.getFavorite(jwtToken);
    }

    @GetMapping("/auth/search/search-list")
    public SearchGetSearchListResponse getSearchHistory(@RequestHeader("Authorization") String jwtToken) {
        return searchService.getSearchList(jwtToken);
    }
}

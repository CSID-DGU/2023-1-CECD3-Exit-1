package com.example.exitmedserver.search.controller;

import com.example.exitmedserver.pill.entity.Pill;
import com.example.exitmedserver.search.dto.SearchTextResponseDto;
import com.example.exitmedserver.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}

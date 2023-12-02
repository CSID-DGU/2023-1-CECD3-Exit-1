package com.example.exitmedserver.search.controller;

import com.example.exitmedserver.search.dto.SearchAddFavoriteRequestDto;
import com.example.exitmedserver.search.dto.SearchAddFavoriteResponseDto;
import com.example.exitmedserver.search.dto.SearchGetImageSearchResponseDto;
import com.example.exitmedserver.search.service.SearchService;
import com.example.exitmedserver.user.dto.SearchGetFavoriteResponse;
import com.example.exitmedserver.user.dto.SearchGetSearchListResponse;
import com.example.exitmedserver.user.dto.SearchTextResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

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

    @PostMapping("/auth/search/image-search")
    public SearchGetImageSearchResponseDto imageEncode(@RequestHeader("Authorization") String jwtToken, @RequestParam("image") MultipartFile image) throws IOException {
        try {
            return searchService.predictImageClassification("690976576624", image, "5170539191523606528");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/auth/search/favorite")
    public SearchGetFavoriteResponse getFavorite(@RequestHeader("Authorization") String jwtToken) {
        return searchService.getFavorite(jwtToken);
    }

    @GetMapping("/auth/search/search-list")
    public SearchGetSearchListResponse getSearchHistory(@RequestHeader("Authorization") String jwtToken) {
        return searchService.getSearchList(jwtToken);
    }

    @GetMapping(
            value = "/test-get-image/{pillItemSequence}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public byte[] getImage(@PathVariable String pillItemSequence) throws IOException {
        return searchService.getImage(pillItemSequence);
    }
}

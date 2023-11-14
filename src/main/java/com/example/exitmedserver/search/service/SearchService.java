package com.example.exitmedserver.search.service;

import com.example.exitmedserver.pill.entity.Pill;
import com.example.exitmedserver.pill.repository.PillImageRepository;
import com.example.exitmedserver.pill.repository.PillRepository;
import com.example.exitmedserver.search.dto.SearchAddFavoriteResponseDto;
import com.example.exitmedserver.search.dto.SearchGetFavoriteResponseDto;
import com.example.exitmedserver.search.dto.SearchGetSearchListResponseDto;
import com.example.exitmedserver.search.dto.SearchTextResponseDto;
import com.example.exitmedserver.search.entity.FavoriteList;
import com.example.exitmedserver.search.entity.SearchHistoryList;
import com.example.exitmedserver.search.repository.FavoriteListRepository;
import com.example.exitmedserver.search.repository.SearchHistoryListRepository;
import com.example.exitmedserver.user.dto.SearchGetFavoriteResponse;
import com.example.exitmedserver.user.dto.SearchGetSearchListResponse;
import com.example.exitmedserver.user.dto.SearchTextResponse;
import com.example.exitmedserver.util.auth.JwtProvider;
import com.example.exitmedserver.util.file.FileProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final PillRepository pillRepository;
    private final PillImageRepository pillImageRepository;
    private final FavoriteListRepository favoriteListRepository;
    private final SearchHistoryListRepository searchHistoryListRepository;

    public SearchTextResponse searchText(String searchText) {
        List<SearchTextResponseDto> searchResults = new ArrayList<>();
        List<Pill> searchedPillList = pillRepository.findPillByPillNameContaining(searchText);

        if (!searchedPillList.isEmpty()) {
            for (Pill p : searchedPillList) {
                SearchTextResponseDto searchTextResponseDto = new SearchTextResponseDto();
                searchTextResponseDto.setPillItemSequence(p.getPillItemSequence());
                searchTextResponseDto.setPillName(p.getPillName());
                searchTextResponseDto.setClassification(p.getClassification());
                searchTextResponseDto.setImage(pillImageRepository.findById(p.getPillItemSequence()).get().getImageLink());
                searchResults.add(searchTextResponseDto);
            }
        }

        SearchTextResponse searchTextResponse = new SearchTextResponse();
        searchTextResponse.setData(searchResults);

        return searchTextResponse;
    }

    public SearchAddFavoriteResponseDto addToFavorite(String jwtToken, Long pillItemSequence) {
        JwtProvider jwtProvider = new JwtProvider();
        String userId = jwtProvider.getUserIdFromToken(jwtToken.replace("Bearer ", ""));

        SearchAddFavoriteResponseDto searchAddFavoriteResponseDto = new SearchAddFavoriteResponseDto();
        searchAddFavoriteResponseDto.setBookMarked(false);
        // FavoriteList favoriteList = new Favor; kyosunim gwajaeguman plz
        FavoriteList favoriteList = favoriteListRepository.findFavoriteListByUserIdAndPillItemSequence(userId, pillItemSequence);
        if (favoriteList == null) {
            favoriteList = FavoriteList.builder()
                    .id(null)
                    .userId(userId)
                    .pillItemSequence(pillItemSequence)
                    .build();
            favoriteListRepository.save(favoriteList);
            searchAddFavoriteResponseDto.setBookMarked(true);
        }

        return searchAddFavoriteResponseDto;
    }

    public SearchGetFavoriteResponse getFavorite(String jwtToken) {
        JwtProvider jwtProvider = new JwtProvider();
        String userId = jwtProvider.getUserIdFromToken(jwtToken.replace("Bearer ", ""));

        List<FavoriteList> searchedFavoriteList = favoriteListRepository.findFavoriteListByUserId(userId);
        List<SearchGetFavoriteResponseDto> favoriteList = new ArrayList<>();

        if (!searchedFavoriteList.isEmpty()) {
            int i = 0;
            for (FavoriteList f : searchedFavoriteList) {
                Optional<Pill> pill = pillRepository.findById(f.getPillItemSequence());
                if (pill.isPresent()) {
                    SearchGetFavoriteResponseDto searchGetFavoriteResponseDto = new SearchGetFavoriteResponseDto();
                    searchGetFavoriteResponseDto.setPillName(pill.get().getPillName());
                    favoriteList.add(searchGetFavoriteResponseDto);
                    i++;
                }
                if (i == 5) {
                    break;
                }
            }
        }

        SearchGetFavoriteResponse searchGetFavoriteResponse = new SearchGetFavoriteResponse();
        searchGetFavoriteResponse.setData(favoriteList);

        return searchGetFavoriteResponse;
    }

    public void addToSearchHistory(String jwtToken, String searchText) {
        JwtProvider jwtProvider = new JwtProvider();
        String userId = jwtProvider.getUserIdFromToken(jwtToken.replace("Bearer ", ""));

        SearchHistoryList searchHistoryList = searchHistoryListRepository.findSearchHistoryListByUserIdAndSearchText(userId, searchText);
        if (searchHistoryList == null) {
            // create new list
            Timestamp createdAt = new Timestamp(System.currentTimeMillis());
            searchHistoryList = SearchHistoryList.builder()
                    .id(null)
                    .userId(userId)
                    .searchText(searchText)
                    .createdAt(createdAt)
                    .build();
            searchHistoryListRepository.save(searchHistoryList);
        } else {
            // update createdAt
            Timestamp createdAt = new Timestamp(System.currentTimeMillis());
            searchHistoryListRepository.delete(searchHistoryList);
            searchHistoryList = SearchHistoryList.builder()
                    .id(null)
                    .userId(userId)
                    .searchText(searchText)
                    .createdAt(createdAt)
                    .build();
            searchHistoryListRepository.save(searchHistoryList);
        }
    }

    public SearchGetSearchListResponse getSearchList(String jwtToken) {
        JwtProvider jwtProvider = new JwtProvider();
        String userId = jwtProvider.getUserIdFromToken(jwtToken.replace("Bearer ", ""));

        List<SearchHistoryList> searchHistoryList = searchHistoryListRepository.findSearchHistoryListByUserIdOrderByCreatedAtDesc(userId);
        List<SearchGetSearchListResponseDto> searchList = new ArrayList<>();

        if (!searchHistoryList.isEmpty()) {
            int i = 0;
            for (SearchHistoryList s : searchHistoryList) {
                SearchGetSearchListResponseDto searchGetSearchListResponseDto = new SearchGetSearchListResponseDto();
                searchGetSearchListResponseDto.setSearchText(s.getSearchText());
                searchList.add(searchGetSearchListResponseDto);
                i++;
                if (i == 5) {
                    break;
                }
            }
        }

        SearchGetSearchListResponse searchGetSearchListResponse = new SearchGetSearchListResponse();
        searchGetSearchListResponse.setData(searchList);

        return searchGetSearchListResponse;
    }

    public byte[] getImage(String pillItemSequence) throws IOException {
        FileProvider fileProvider = new FileProvider();
        return Files.readAllBytes(new File(fileProvider.getServerDisplayPath() + "/" + pillItemSequence + ".jpg").toPath());
    }
}

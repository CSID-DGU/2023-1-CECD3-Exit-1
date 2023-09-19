package com.example.exitmedserver.search.service;

import com.example.exitmedserver.pill.entity.Pill;
import com.example.exitmedserver.pill.repository.PillRepository;
import com.example.exitmedserver.search.dto.SearchGetFavoriteResponseDto;
import com.example.exitmedserver.search.dto.SearchTextResponseDto;
import com.example.exitmedserver.search.entity.FavoriteList;
import com.example.exitmedserver.search.repository.FavoriteListRepository;
import com.example.exitmedserver.util.auth.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final PillRepository pillRepository;
    private final FavoriteListRepository favoriteListRepository;

    public List<SearchTextResponseDto> searchText(String searchText) {
        SearchTextResponseDto searchTextResponseDto = new SearchTextResponseDto();
        List<SearchTextResponseDto> searchResults = new ArrayList<>();
        List<Pill> searchedPillList = pillRepository.findPillByPillNameContaining(searchText);

        if (!searchedPillList.isEmpty()) {
            for (Pill p : searchedPillList) {
                searchTextResponseDto.setPillItemSequence(p.getPillItemSequence());
                searchResults.add(searchTextResponseDto);
            }
        }

        return searchResults;
    }

    public boolean addToFavorite(String jwtToken, Long pillItemSequence) {
        JwtProvider jwtProvider = new JwtProvider();
        String userId = jwtProvider.getUserIdFromToken(jwtToken.replace("Bearer ", ""));
        // FavoriteList favoriteList = new Favor; kyosunim gwajaeguman plz
        FavoriteList favoriteList = favoriteListRepository.findFavoriteListByUserIdAndPillItemSequence(userId, pillItemSequence);
        if (favoriteList == null) {
            favoriteList = FavoriteList.builder()
                    .id(null)
                    .userId(userId)
                    .pillItemSequence(pillItemSequence)
                    .build();
            favoriteListRepository.save(favoriteList);
        }

        return true;
    }

    public List<SearchGetFavoriteResponseDto> getFavorite(String jwtToken) {
        List<SearchGetFavoriteResponseDto> favoriteList = new ArrayList<>();
        SearchGetFavoriteResponseDto searchGetFavoriteResponseDto = new SearchGetFavoriteResponseDto();

        JwtProvider jwtProvider = new JwtProvider();
        String userId = jwtProvider.getUserIdFromToken(jwtToken.replace("Bearer ", ""));

        //System.out.println(pillRepository.findById(195900043L).get());
        List<FavoriteList> searchedFavoriteList = favoriteListRepository.findFavoriteListByUserId(userId);
        if (!searchedFavoriteList.isEmpty()) {
            for (FavoriteList f : searchedFavoriteList) {
                Optional<Pill> pill = pillRepository.findById(f.getPillItemSequence());
                pill.ifPresent(value -> searchGetFavoriteResponseDto.setPillName(value.getPillName()));

                favoriteList.add(searchGetFavoriteResponseDto);
            }
        }
        System.out.println(favoriteList);

        return favoriteList;
    }
}

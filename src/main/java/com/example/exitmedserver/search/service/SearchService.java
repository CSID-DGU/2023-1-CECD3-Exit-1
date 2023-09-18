package com.example.exitmedserver.search.service;

import com.example.exitmedserver.pill.entity.Pill;
import com.example.exitmedserver.pill.repository.PillRepository;
import com.example.exitmedserver.search.dto.SearchTextRequestDto;
import com.example.exitmedserver.search.dto.SearchTextResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final PillRepository pillRepository;

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
}

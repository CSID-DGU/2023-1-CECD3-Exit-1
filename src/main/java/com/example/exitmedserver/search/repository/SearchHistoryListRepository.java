package com.example.exitmedserver.search.repository;

import com.example.exitmedserver.search.entity.SearchHistoryList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchHistoryListRepository extends JpaRepository<SearchHistoryList, String> {
}

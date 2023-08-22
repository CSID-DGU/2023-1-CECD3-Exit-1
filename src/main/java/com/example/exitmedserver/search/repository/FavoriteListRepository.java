package com.example.exitmedserver.search.repository;

import com.example.exitmedserver.search.entity.FavoriteList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteListRepository extends JpaRepository<FavoriteList, String> {
}

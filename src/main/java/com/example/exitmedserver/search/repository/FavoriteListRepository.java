package com.example.exitmedserver.search.repository;

import com.example.exitmedserver.search.entity.FavoriteList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteListRepository extends JpaRepository<FavoriteList, String> {
    public List<FavoriteList> findFavoriteListByUserId(String userId);

    public FavoriteList findFavoriteListByUserIdAndPillItemSequence(String userId, Long pillItemSequence);
}

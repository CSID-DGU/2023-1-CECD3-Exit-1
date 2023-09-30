package com.example.exitmedserver.pill.repository;

import com.example.exitmedserver.pill.entity.Drawer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrawerRepository extends JpaRepository<Drawer, Integer> {
    Drawer findDrawerByUserIdAndPillItemSequence(String userId, Long pillItemSequence);
    List<Drawer> findDrawerByUserId(String userId);
}

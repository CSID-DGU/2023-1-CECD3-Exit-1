package com.example.exitmedserver.pill.repository;

import com.example.exitmedserver.pill.entity.Pill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PillRepository extends JpaRepository<Pill, Long> {
    List<Pill> findPillByPillNameContaining(String searchText);
    Pill findPillByPillItemSequence(Long pillItemSequence);
}

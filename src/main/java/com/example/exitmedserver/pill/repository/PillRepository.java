package com.example.exitmedserver.pill.repository;

import com.example.exitmedserver.pill.entity.Pill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PillRepository extends JpaRepository<Pill, Long> {
}

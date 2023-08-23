package com.example.exitmedserver.pill.repository;

import com.example.exitmedserver.pill.entity.PillImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PillImageRepository extends JpaRepository<PillImage, Long> {
}

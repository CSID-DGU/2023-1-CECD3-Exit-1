package com.example.exitmedserver.pill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PillImageRepository extends JpaRepository<PillImage, Long> {
}

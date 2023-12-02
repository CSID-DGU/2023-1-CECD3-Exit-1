package com.example.exitmedserver.pill.repository;

import com.example.exitmedserver.pill.entity.MaxIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaxIngredientRepository extends JpaRepository<MaxIngredient, Integer> {
}

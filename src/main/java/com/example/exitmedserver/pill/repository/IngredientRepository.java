package com.example.exitmedserver.pill.repository;

import com.example.exitmedserver.pill.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    List<Ingredient> findIngredientByPillItemSequence(Long pillItemSequence);
}

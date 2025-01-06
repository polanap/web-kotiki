package org.example.kotiki.infrastructure.dao;

import org.example.kotiki.infrastructure.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodDAO extends JpaRepository<Food, Integer> {
}

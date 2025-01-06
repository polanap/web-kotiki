package org.example.kotiki.infrastructure.dao;

import org.example.kotiki.application.cosmetic.CosmeticType;
import org.example.kotiki.infrastructure.domain.Cosmetic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.example.kotiki.application.cosmetic.CosmeticType;

import java.util.ArrayList;

@Repository
public interface CosmeticDAO extends JpaRepository<Cosmetic, Integer> {
    @Query("""
    select c from Cosmetic c where c.type = :cosmetic
""")
    public ArrayList<Cosmetic> getByType(CosmeticType cosmetic);

    @Query("""
    select c from Cosmetic c where c.rarity = 1
""")
    public ArrayList<Cosmetic> getDefaultCosmetics();

}

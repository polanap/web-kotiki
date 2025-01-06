package org.example.kotiki.infrastructure.dao;

import org.example.kotiki.infrastructure.domain.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsDAO extends JpaRepository<Stats, Integer> {
    @Query("""
    SELECT s FROM Stats s JOIN Cat c ON s.id = c.statId
""")
    public Stats getByCatId(Integer catId);
}

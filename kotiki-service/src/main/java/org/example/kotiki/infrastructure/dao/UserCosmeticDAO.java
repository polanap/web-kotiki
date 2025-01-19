package org.example.kotiki.infrastructure.dao;

import org.example.kotiki.application.cosmetic.CosmeticType;
import org.example.kotiki.infrastructure.domain.UsersCosmetic;
import org.example.kotiki.infrastructure.dto.CosmeticDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCosmeticDAO extends JpaRepository<UsersCosmetic, Integer> {

    @Query("""
    select u from UsersCosmetic u where u.userId = :id
""")
    List<UsersCosmetic> getAvaliableUserCosmetics(Integer id);

    @Query("""
    select 
    new org.example.kotiki.infrastructure.dto.CosmeticDTO(
        c.id,
        c.type,
        c.value,
        c.rarity,
        c.set,
        c.urlKey
    )
     from UsersCosmetic u join Cosmetic c on u.cosmeticId = c.id where u.userId = :id and c.type = :type
""")
    List<CosmeticDTO> getUserCosmeticsByType(Integer id, CosmeticType type);
}
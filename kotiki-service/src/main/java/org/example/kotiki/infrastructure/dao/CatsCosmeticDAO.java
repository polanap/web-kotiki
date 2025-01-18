package org.example.kotiki.infrastructure.dao;

import org.example.kotiki.application.cosmetic.CosmeticType;
import org.example.kotiki.infrastructure.domain.CatsCosmetic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CatsCosmeticDAO extends JpaRepository<CatsCosmetic, Integer> {

    @Query("""
            select cc from CatsCosmetic cc 
            join UsersCosmetic uc on cc.id = uc.cosmeticId 
            where uc.cosmeticId = :cosmeticId and uc.userId = :userId
            """)
    public CatsCosmetic getByUserAndCosmeticId(Integer cosmeticId, Integer userId);

    @Modifying
    @Query("""
            delete CatsCosmetic cc
            where cc.catId = :catId and cc.cosmeticType = :type
            """)
    public void deleteAllByTypeAndCatId(Integer catId, CosmeticType type);
}

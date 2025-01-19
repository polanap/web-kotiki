package org.example.kotiki.infrastructure.dao;

import org.example.kotiki.infrastructure.domain.Cat;
import org.example.kotiki.infrastructure.domain.CatsCosmetic;
import org.example.kotiki.infrastructure.domain.UsersCosmetic;
import org.example.kotiki.infrastructure.dto.CosmeticDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CatDAO extends JpaRepository<Cat, Integer> {

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
    from CatsCosmetic cc join UsersCosmetic uc 
    on cc.userCosmeticId=uc.id join Cosmetic c on uc.cosmeticId=c.id where cc.catId= :catId
""")
    public ArrayList<CosmeticDTO> getAppliedCosmetics(Integer catId);

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
    from CatsCosmetic cc join UsersCosmetic uc 
    on cc.userCosmeticId=uc.id join Cosmetic c on uc.cosmeticId=c.id where cc in :cosmetics
""")
    public ArrayList<CosmeticDTO> getCosmeticsByCatsCosmetic(List<CatsCosmetic> cosmetics);

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
     from UsersCosmetic uc join Cosmetic c on uc.cosmeticId=c.id where uc in :cosmetics
""")
    public ArrayList<CosmeticDTO> getCosmeticsByUsersCosmetic(ArrayList<UsersCosmetic> cosmetics);

//    public ArrayList<Cosmetic> getCatsCosmetic(Integer cat_id){
//        return new ArrayList<>();
//    }
//    public void setCosmetic(Integer cosmetic_id){
//    }
//    public void deleteCosmetic(Integer cosmetic_id){
//    }
}

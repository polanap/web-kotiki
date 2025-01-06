package org.example.kotiki.application.cat;

import org.example.kotiki.application.cosmetic.CosmeticType;
import org.example.kotiki.infrastructure.domain.Cat;
import org.example.kotiki.infrastructure.domain.Cosmetic;
import org.example.kotiki.infrastructure.domain.Food;
import org.example.kotiki.infrastructure.domain.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface CatInterfase {
    public Double toFeed(Food food, Cat cat);
    public Cat getCat(Integer id);
    public LocalDateTime getCatsAge(Cat cat);
    public ArrayList<Cosmetic> getCatsCosmetic(Cat cat);
    public ArrayList<Cosmetic> applyCosmetic(Integer catId, ArrayList<Integer> cosmeticIds, User user);
    public ArrayList<Cosmetic> disapplyCosmetic(Integer catId, ArrayList<Integer> cosmeticIds);
}

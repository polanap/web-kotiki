package org.example.kotiki.infrastructure.service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;

import org.example.kotiki.application.cosmetic.CosmeticInterface;
import org.example.kotiki.application.cosmetic.CosmeticType;
import org.example.kotiki.infrastructure.dao.CosmeticDAO;
import org.example.kotiki.infrastructure.dao.UserCosmeticDAO;
import org.example.kotiki.infrastructure.dao.UserDAO;
import org.example.kotiki.infrastructure.domain.Cosmetic;
import org.example.kotiki.infrastructure.domain.User;
import org.example.kotiki.infrastructure.domain.UsersCosmetic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CosmeticService implements CosmeticInterface {
    @Autowired
    CosmeticDAO cosmeticDAO;
    @Autowired
    UserCosmeticDAO userCosmeticDAO;

    @Getter
    private ArrayList<Cosmetic> defaultCosmetics;


    @PostConstruct
    public void getDefaultCosmeticsFromDatabase() {
        defaultCosmetics = cosmeticDAO.getDefaultCosmetics();
    }

    public void setDefaultCosmeticsToUser(User user){
        setCosmetics(defaultCosmetics, user);
    }

    @Override
    public ArrayList<CosmeticType> getAllCosmetics() {
        return new ArrayList<>(List.of(CosmeticType.values()));
    }

    @Override
    public ArrayList<Cosmetic> getCosmetic(CosmeticType cosmetic) {
        return cosmeticDAO.getByType(cosmetic);
    }

    public void setCosmetics(List<Cosmetic> cosmetics, User user) {
        cosmetics.stream()
                .map(cosmetic -> new UsersCosmetic(null, cosmetic.getId(), user.getId(), cosmetic.getType()))
                .forEach(cosmetic -> userCosmeticDAO.save(cosmetic));
    }
    
}

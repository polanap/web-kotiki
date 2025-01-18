package org.example.kotiki.infrastructure.domain.utils;

import org.example.kotiki.application.cosmetic.CosmeticSet;
import org.example.kotiki.infrastructure.dao.CatDAO;
import org.example.kotiki.infrastructure.dao.StatsDAO;
import org.example.kotiki.infrastructure.domain.Cat;
import org.example.kotiki.infrastructure.domain.CatsCosmetic;
import org.example.kotiki.infrastructure.domain.Cosmetic;
import org.example.kotiki.infrastructure.domain.Stats;
import org.example.kotiki.infrastructure.domain.User;
import org.example.kotiki.infrastructure.service.CatService;
import org.example.kotiki.infrastructure.service.CosmeticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultCatBuilder {

    @Autowired
    StatsDAO statsDAO;

    @Autowired
    CatDAO catDAO;

    @Autowired
    CosmeticService cosmeticService;

    @Autowired
    CatService catService;

    private final String [] defaultNames = {"Безымяныш", "Котяш-безымяш", "Мистер Инкогнито"};


    public Stats builtStat(LocalDateTime initTime){
        Stats stats = new Stats();
        stats.setLastFeed(initTime);
        stats.setLastFun(initTime);
        stats.setLastHappyDegree(1);
        stats.setLastSatietyDegree(1);
        statsDAO.save(stats);
        return stats;
    }

    public void applyStartCosmetics(Integer catId, User owner){
        List<Integer> startCosmetics = cosmeticService.getDefaultCosmetics()
            .stream()
            .filter(cosmetic -> cosmetic.getSet()==CosmeticSet.START)
            .map(cosmetic -> cosmetic.getId())
            .toList();
        catService.applyCosmetics(catId, startCosmetics, owner);

    }

    public Cat build(User owner){
        Cat cat = new Cat();
        cat.setBirth(owner.getRegistrationDate());
        cat.setName(defaultNames[(int) (Math.random()*defaultNames.length)]);
        var stats = builtStat(owner.getRegistrationDate());
        cat.setStatId(stats.getId());
        cat.setOwnerId(owner.getId());
        catDAO.save(cat);
        applyStartCosmetics(cat.getId(), owner);
        return cat;
    }
}

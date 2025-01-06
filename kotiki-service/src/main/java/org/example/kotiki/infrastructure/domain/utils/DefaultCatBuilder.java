package org.example.kotiki.infrastructure.domain.utils;

import org.example.kotiki.infrastructure.dao.CatDAO;
import org.example.kotiki.infrastructure.dao.StatsDAO;
import org.example.kotiki.infrastructure.domain.Cat;
import org.example.kotiki.infrastructure.domain.Stats;
import org.example.kotiki.infrastructure.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Component
public class DefaultCatBuilder {

    @Autowired
    StatsDAO statsDAO;

    @Autowired
    CatDAO catDAO;

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

    public Cat build(User owner){
        Cat cat = new Cat();
        cat.setBirth(owner.getRegistrationDate());
        cat.setName(defaultNames[(int) (Math.random()*defaultNames.length)]);
        var stats = builtStat(owner.getRegistrationDate());
        cat.setStatId(stats.getId());
        cat.setOwnerId(owner.getId());
        catDAO.save(cat);
        return cat;
    }
}

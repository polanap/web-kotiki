package org.example.kotiki.infrastructure.service;

import org.example.kotiki.application.cosmetic.CosmeticType;
import org.example.kotiki.infrastructure.dao.*;
import org.example.kotiki.infrastructure.domain.*;
import org.example.kotiki.infrastructure.dto.CosmeticDTO;
import org.example.kotiki.infrastructure.dto.SendCatsCosmeticsDTO;
import org.example.kotiki.infrastructure.dto.StatDTO;
import org.example.kotiki.infrastructure.dto.UserCosmeticsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class CatService{
    private final double satietyPerSec = 0.0017;
    private final double happyPerSec = 0.0017;
    private final double foodPoints = 0.1;
    private final double happyPoints = 0.1;

    @Autowired
    CatDAO catDAO;
    @Autowired
    StatsDAO statsDAO;
    @Autowired
    CatsCosmeticDAO catsCosmeticDAO;
    @Autowired
    UserCosmeticDAO userCosmeticDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CosmeticDAO cosmeticDAO;


//    public Double toFeed(Food food, Cat cat) {
//        Stats catStats = statsDAO.getByCatId(cat.getId());
//        Double satietyDegree = catStats.getSatietyDegree();
//        Double nutritional = food.getNutritional();
//        satietyDegree += nutritional;
//        catStats.setSatietyDegree(satietyDegree);
//        statsDAO.save(catStats);
//
//        return satietyDegree;
//    }

    public Cat getCat(Integer id){
        return catDAO.getById(id);
    }

    public ArrayList<CosmeticDTO> getCatsCosmetics(Integer catId){
        return catDAO.getAppliedCosmetics(catId);
    }

    @Transactional
    public ArrayList<CosmeticDTO> applyCosmetics(Integer catId, SendCatsCosmeticsDTO request, User user){
        ArrayList<Integer> cosmeticIds = request.getCosmetics();
        List<CatsCosmetic> cosmetics = userCosmeticDAO.getAvaliableUserCosmetics(user.getId())
                .stream()
                .filter(cosm -> cosmeticIds.contains(cosm.getUserId()))
                .map(cosm -> new CatsCosmetic(catId, cosm.getId(), cosm.getCosmeticType()))
                .map(cosm -> catsCosmeticDAO.save(cosm))
                .toList();
        return catDAO.getCosmeticsByCatsCosmetic(cosmetics);
    }

    public CosmeticDTO disapplyCosmetic(Integer catId, Integer cosmId, User user){
        CatsCosmetic cosmetic = catsCosmeticDAO.getByUserAndCosmeticId(cosmId, user.getId());
        if(cosmetic == null){ return null;  }
        if(!catsCosmeticDAO.existsById(cosmetic.getId())){
            return null;
        }
        catsCosmeticDAO.delete(cosmetic);
        return new CosmeticDTO(cosmeticDAO.getById(cosmId));
    }

    public StatDTO getStat(Integer catId){
        Stats stats = statsDAO.getByCatId(catId);
        double satiety = stats.getLastSatietyDegree() -
                Duration.between(stats.getLastFeed(), LocalDateTime.now()).getSeconds() * satietyPerSec;
        satiety = (satiety > 0) ? satiety : 0;
        double happy = stats.getLastHappyDegree() -
                Duration.between(stats.getLastFun(), LocalDateTime.now()).getSeconds() * happyPerSec;
        happy = (happy > 0) ? happy : 0;

        return new StatDTO(catId, satiety, happy);
    }

    public StatDTO toFeedCat(Integer catId){
        Stats stats = statsDAO.getByCatId(catId);
        double satiety = stats.getLastSatietyDegree() -
                Duration.between(stats.getLastFeed(), LocalDateTime.now()).getSeconds() * satietyPerSec + foodPoints;
        satiety = (satiety > 0) ? satiety : 0;
        double happy = stats.getLastHappyDegree() -
                Duration.between(stats.getLastFun(), LocalDateTime.now()).getSeconds() * happyPerSec;
        happy = (happy > 0) ? happy : 0;

        return new StatDTO(catId, satiety, happy);
    }

    public StatDTO toPetCat(Integer catId){
        Stats stats = statsDAO.getByCatId(catId);
        double satiety = stats.getLastSatietyDegree() -
                Duration.between(stats.getLastFeed(), LocalDateTime.now()).getSeconds() * satietyPerSec;
        satiety = (satiety > 0) ? satiety : 0;
        double happy = stats.getLastHappyDegree() -
                Duration.between(stats.getLastFun(), LocalDateTime.now()).getSeconds() * happyPerSec + happyPoints;
        happy = (happy > 0) ? happy : 0;

        return new StatDTO(catId, satiety, happy);
    }

}

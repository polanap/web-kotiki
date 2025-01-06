package org.example.kotiki.application.cosmetic;

import org.example.kotiki.infrastructure.domain.Cosmetic;
import org.example.kotiki.infrastructure.domain.User;

import java.util.ArrayList;

public interface CosmeticInterface {

    //все доступные виды косметиков
    public ArrayList<CosmeticType> getAllCosmetics();

    //все косметики одного типа
    public ArrayList<Cosmetic> getCosmetic(CosmeticType cosmetic);

}

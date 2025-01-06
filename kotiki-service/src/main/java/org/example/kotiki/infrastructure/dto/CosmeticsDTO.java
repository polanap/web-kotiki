package org.example.kotiki.infrastructure.dto;

import lombok.Data;
import org.example.kotiki.application.cosmetic.CosmeticType;

import java.util.ArrayList;

@Data
public class CosmeticsDTO {
    CosmeticType[] cosmetics;
}

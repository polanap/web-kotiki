package org.example.kotiki.infrastructure.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import jakarta.persistence.*;
import org.example.kotiki.application.cosmetic.CosmeticType;

@Entity
@Table(name = "cats_cosmetic", uniqueConstraints={
        @UniqueConstraint(columnNames = {"catId", "cosmetic_type"})
})
@Data
@NoArgsConstructor
public class CatsCosmetic {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cat_id")
    private Integer catId;

    @Column(name = "user_cosmetic_id")
    private Integer userCosmeticId;

    @Enumerated(EnumType.STRING)
    @Column(name = "cosmetic_type")
    private CosmeticType cosmeticType;

    public CatsCosmetic(Integer catId, Integer userCosmeticId, CosmeticType cosmeticType) {
        this.catId = catId;
        this.userCosmeticId = userCosmeticId;
        this.cosmeticType = cosmeticType;
    }
}

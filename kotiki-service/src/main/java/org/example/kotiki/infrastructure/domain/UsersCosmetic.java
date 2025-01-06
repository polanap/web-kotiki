package org.example.kotiki.infrastructure.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import jakarta.persistence.*;
import org.example.kotiki.application.cosmetic.CosmeticType;

@Table(name = "user_cosmetic")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersCosmetic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cosmetic_id")
    private Integer cosmeticId;

    @Column(name = "user_id")
    private Integer userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "cosmetic_type")
    private CosmeticType cosmeticType;
}

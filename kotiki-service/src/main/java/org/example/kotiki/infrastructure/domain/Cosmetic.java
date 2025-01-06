package org.example.kotiki.infrastructure.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kotiki.application.cosmetic.CosmeticType;
import org.example.kotiki.application.cosmetic.CosmeticSet;

import jakarta.persistence.*;

@Entity
@Table(name = "cosmetic")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cosmetic {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CosmeticType type;

    @Column(name = "value")
    private String value;

    @Column(name = "rarity")
    private Double rarity;

    @Enumerated(EnumType.STRING)
    @Column(name = "set")
    private CosmeticSet set;
}

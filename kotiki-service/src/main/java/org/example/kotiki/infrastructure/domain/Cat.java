package org.example.kotiki.infrastructure.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.example.kotiki.application.cosmetic.Breed;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "cat")
@NoArgsConstructor
@Data
public class Cat {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth")
    private LocalDateTime birth;

    @Column(name = "owner_id")
    private Integer ownerId;

    @Column(name = "stat_id")
    private Integer statId;

}

package org.example.kotiki.infrastructure.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@Table(name = "stats")
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "last_satiety_degree")
    private double lastSatietyDegree;

    @Column(name = "last_happy_degree")
    private double lastHappyDegree;

    @Column(name = "last_feed")
    private LocalDateTime lastFeed;

    @Column(name = "happy_fun")
    private LocalDateTime lastFun;
}

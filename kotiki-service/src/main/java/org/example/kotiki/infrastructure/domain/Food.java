package org.example.kotiki.infrastructure.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import jakarta.persistence.*;

@Entity
@Table(name = "food")
@Data
@NoArgsConstructor
public class Food {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "nutritional")
    Double nutritional;
}

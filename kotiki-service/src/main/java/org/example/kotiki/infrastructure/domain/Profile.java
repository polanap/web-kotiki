package org.example.kotiki.infrastructure.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "profile")
@Data
@NoArgsConstructor
public class Profile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "birth")
    private LocalDateTime birth;

    // text!!!
    @Lob
    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "favorite_cat_breed")
    private String favoriteCatBreed;
}

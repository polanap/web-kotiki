package org.example.kotiki.infrastructure.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "friends")
public class Friends {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_user_id")
    private Integer first_user_id;

    @Column(name = "second_user_id")
    private Integer second_user_id;

    /**
     -1 -- первый пользователь подписан на второго
     0 -- друзья
     1 -- второй пользователь подписан на первого
      */
    @Column(name = "relation_type")
    private Integer relation_type;
}

package org.example.kotiki.infrastructure.dao;

import org.example.kotiki.infrastructure.domain.User;
import org.example.kotiki.infrastructure.dto.CatDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

@Repository
public interface UserDAO extends JpaRepository<User, Integer>{
    @Query("""
    select u from User u where u.username = :username
""")
    User findByUsername (String username);

    @Query("""
    select c from Cat c where c.ownerId = :id
""")
    ArrayList<CatDTO> getCats(Integer id);
}

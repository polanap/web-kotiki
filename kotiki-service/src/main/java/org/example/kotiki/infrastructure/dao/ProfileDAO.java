package org.example.kotiki.infrastructure.dao;

import org.example.kotiki.infrastructure.domain.Profile;
import org.example.kotiki.infrastructure.dto.ProfileDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ProfileDAO extends JpaRepository<Profile, Integer> {

    @Query("""
    select new org.example.kotiki.infrastructure.dto.ProfileDTO(p)
     from Profile p where p.userId = :userId
""")
    public ProfileDTO findByUserId(Integer userId);

    @Modifying
    @Query("""
    update Profile p set 
    p.firstname = :firstName,
    p.lastname = :lastName,
    p.birth = :birth,
    p.aboutMe = :aboutMe,
    p.favoriteCatBreed = :favoriteCatBreed
    where p.userId = :userId
""")
    public void updateByUserId(
            Integer userId,
            String firstName,
            String lastName,
            LocalDateTime birth,
            String aboutMe,
            String favoriteCatBreed
    );
}

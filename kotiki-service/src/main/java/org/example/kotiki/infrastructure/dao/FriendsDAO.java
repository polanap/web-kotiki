package org.example.kotiki.infrastructure.dao;

import org.example.kotiki.infrastructure.domain.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsDAO extends JpaRepository<Friends, Integer> {
}

package org.example.kotiki.infrastructure.dao;

import org.example.kotiki.infrastructure.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends JpaRepository<Role, Integer> {
}
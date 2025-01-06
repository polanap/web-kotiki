package org.example.kotiki.infrastructure.service;

import org.example.kotiki.infrastructure.dao.FriendsDAO;
import org.example.kotiki.infrastructure.domain.Friends;
import org.example.kotiki.infrastructure.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendsService {
    @Autowired
    FriendsDAO friendsDAO;

    public boolean isFriends(User first, User second) {
        return false;
    }
}

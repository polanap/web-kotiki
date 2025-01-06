package org.example.kotiki.application.user;

import org.example.kotiki.infrastructure.domain.Cat;
import org.example.kotiki.infrastructure.domain.Profile;
import org.example.kotiki.infrastructure.domain.User;

import java.util.ArrayList;

public interface UserInterfase {
    public Profile getUserProfile(User user);
    public User getUserById(Integer id);
    public ArrayList<User> getUserFriends(User user);
    public ArrayList<Cat> getUsersCats(User user);
}

package org.example.kotiki.infrastructure.service;

import lombok.Getter;
import org.example.kotiki.application.cosmetic.CosmeticType;
import org.example.kotiki.infrastructure.dao.*;
import org.example.kotiki.infrastructure.domain.*;
import org.example.kotiki.infrastructure.domain.utils.DefaultCatBuilder;
import org.example.kotiki.infrastructure.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserDAO userRepository;
    @Autowired
    RoleDAO roleRepository;
    @Autowired
    CosmeticService cosmeticService;
    @Autowired
    DefaultCatBuilder catBuilder;

    @Getter
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private ProfileDAO profileDAO;
    @Autowired
    private UserCosmeticDAO userCosmeticDAO;

    public CatsDTO getCats(User user){
        ArrayList<CatDTO> cats = userRepository.getCats(user.getId());
        return new CatsDTO(cats);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User findUserById(Integer userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public User saveUser(UserRegistrationDTO request) throws ValidationException {
        if (request.getUsername()==null){
            throw new ValidationException("Username is required");
        }
        if (request.getPassword()==null){
            throw new ValidationException("Password is required");
        }
        if (request.getConfirmPassword()==null){
            throw new ValidationException("Confirm password is required");
        }
        if (!request.getPassword().equals(request.getConfirmPassword())){
            throw new ValidationException("Passwords not equals");
        }

        if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new ValidationException("User with this username already exist");
        }
        if (request.getUsername().length()<3 && request.getUsername().length()>30){
            throw new ValidationException("username must be less than or equal to 30");
        }
        if (request.getPassword().length()<3 && request.getPassword().length()>30){
            throw new ValidationException("password must be less than or equal to 30");
        }

        User user = new User();
        user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRegistrationDate(LocalDateTime.now());
        return userRepository.save(user);

    }

    public boolean deleteUser(Integer userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public void setDefaultCat(User user){
        catBuilder.build(user);
    }

    public void makeProfile(User user){
        Profile profile = new Profile();
        profile.setUserId(user.getId());
        profileDAO.save(profile);
    }

    @Transactional
    public void saveUserWithDefaults(UserRegistrationDTO request) throws ValidationException {
        User user = this.saveUser(request);
        cosmeticService.setDefaultCosmeticsToUser(user);
        this.makeProfile(user);
        this.setDefaultCat(user);
    }

    public ProfileDTO getProfile(Integer userId) {
        ProfileDTO profile = profileDAO.findByUserId(userId);
        return profile;
    }

    public ProfileDTO updateProfile(ProfileDTO request, Integer userId) {
        profileDAO.updateByUserId(userId, request.getFirstname(), request.getLastname(),
                request.getBirth(), request.getAboutMe(), request.getFavoriteCatBreed());
        return profileDAO.findByUserId(userId);
    }
    public UserCosmeticsDTO getUserCosmeticsByType(Integer userId, CosmeticType type) {
        List<CosmeticDTO> cosmetics = userCosmeticDAO.getUserCosmeticsByType(userId, type.toString().toUpperCase());
        return new UserCosmeticsDTO(cosmetics);
    }

    public User getUser(Integer id){
        return userRepository.findById(id).orElse(null);
    }

}

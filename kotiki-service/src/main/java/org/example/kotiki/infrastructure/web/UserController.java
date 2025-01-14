package org.example.kotiki.infrastructure.web;

import org.example.kotiki.application.cosmetic.CosmeticType;
import org.example.kotiki.infrastructure.domain.User;
import org.example.kotiki.infrastructure.dto.CatsDTO;
import org.example.kotiki.infrastructure.dto.UserCosmeticsDTO;
import org.example.kotiki.infrastructure.service.FriendsService;
import org.example.kotiki.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FriendsService friendsService;

    @GetMapping("/cats")
    public ResponseEntity getUserCats(@RequestParam(required=false) Integer userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User curentUser = (User) authentication.getPrincipal();
        User userToFindCats = null;
        if (userId!=null){
            User requestedUser = userService.findUserById(userId);
            if (!friendsService.isFriends(curentUser, requestedUser)){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            userToFindCats = requestedUser;
        }

        CatsDTO cats = userService.getCats(userToFindCats);
        return new ResponseEntity<>(cats, HttpStatus.OK);

    }
    @GetMapping("/cosmetics")
    public ResponseEntity getUserCosmeticsByType(@RequestParam(required=false) Integer userId, @RequestParam String type) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User curentUser = (User) authentication.getPrincipal();
        Integer userToFindCosmetics = null;
        if (userId!=null){
            User requestedUser = userService.findUserById(userId);
            if (!friendsService.isFriends(curentUser, requestedUser)){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            userToFindCosmetics = requestedUser.getId();
        }
        CosmeticType cosmeticType;
        try {
            cosmeticType = CosmeticType.valueOf(type);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserCosmeticsDTO cosmetics = userService.getUserCosmeticsByType(userToFindCosmetics, cosmeticType);
        return new ResponseEntity<>(cosmetics, HttpStatus.OK);

    }




}

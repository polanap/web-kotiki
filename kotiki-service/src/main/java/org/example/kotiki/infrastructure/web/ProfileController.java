package org.example.kotiki.infrastructure.web;

import org.example.kotiki.infrastructure.domain.User;
import org.example.kotiki.infrastructure.dto.ErrorMessageDTO;
import org.example.kotiki.infrastructure.dto.ProfileDTO;
import org.example.kotiki.infrastructure.service.FriendsService;
import org.example.kotiki.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    UserService userService;

    @Autowired
    FriendsService friendsService;

    @GetMapping("/")
    public ResponseEntity getProfile(@RequestParam(required=false) Integer userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User curentUser = (User) authentication.getPrincipal();
        Integer userToFindProfileId = null;
        if (userId!=null){
            User requestedUser = userService.findUserById(userId);
            if (!friendsService.isFriends(curentUser, requestedUser)){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            userToFindProfileId = requestedUser.getId();
        }

        ProfileDTO profile = userService.getProfile(userToFindProfileId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity updateProfile(@RequestBody ProfileDTO request, @RequestParam Integer userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User curentUser = (User) authentication.getPrincipal();
        Integer userToFindProfileId = null;
        if (userId!=null){
            User requestedUser = userService.findUserById(userId);
            if (!friendsService.isFriends(curentUser, requestedUser)){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            userToFindProfileId = requestedUser.getId();
        }
        try{
            request.validate();
        }catch (ValidationException e){
            return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        ProfileDTO profile = userService.updateProfile(request, userToFindProfileId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

}

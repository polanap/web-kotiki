package org.example.kotiki.infrastructure.web;

import org.example.kotiki.infrastructure.domain.Cat;
import org.example.kotiki.infrastructure.domain.User;
import org.example.kotiki.infrastructure.dto.CatsCosmeticsDTO;
import org.example.kotiki.infrastructure.dto.CosmeticDTO;
import org.example.kotiki.infrastructure.dto.SendCatsCosmeticsDTO;
import org.example.kotiki.infrastructure.exceptions.TypeException;
import org.example.kotiki.infrastructure.service.CatService;
import org.example.kotiki.infrastructure.service.FriendsService;
import org.example.kotiki.infrastructure.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/cat")
public class CatController {

    private final CatService catService;
    private final FriendsService friendsService;
    private final UserService userService;

    public CatController(CatService catService, FriendsService friendsService, UserService userService) {
        this.catService = catService;
        this.friendsService = friendsService;
        this.userService = userService;
    }

    @GetMapping("/cosmetics")
    public ResponseEntity getCatCosmetics(@RequestParam Integer catId) {
        if(!catService.existCat(catId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User curentUser = (User) authentication.getPrincipal();
        Cat cat = catService.getCat(catId);
        if (cat.getOwnerId() != curentUser.getId()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        ArrayList<CosmeticDTO> cosmetics = catService.getCatsCosmetics(catId);
        var responseBody = new CatsCosmeticsDTO(catId, cosmetics);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PostMapping("/cosmetics")
    public ResponseEntity applyCatCosmetics(@RequestBody SendCatsCosmeticsDTO request, @RequestParam Integer catId) {
        if(!catService.existCat(catId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User curentUser = (User) authentication.getPrincipal();
        Cat cat = catService.getCat(catId);
        if (cat.getOwnerId() != curentUser.getId()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        try{
            ArrayList<CosmeticDTO> cosmetics = catService.applyCosmeticsByRequest(catId, request, curentUser);
            var responseBody = new CatsCosmeticsDTO(catId, cosmetics);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);  
        }catch (TypeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  
        }
        // ArrayList<CosmeticDTO> cosmetics = catService.applyCosmeticsByRequest(catId, request, curentUser);
        // var responseBody = new CatsCosmeticsDTO(catId, cosmetics);
        // return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @DeleteMapping("/cosmetics")
    public ResponseEntity disapplyCosmetic(@RequestParam Integer catId, @RequestParam Integer cosmId) {
        if(!catService.existCat(catId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User curentUser = (User) authentication.getPrincipal();
        Cat cat = catService.getCat(catId);
        if (cat.getOwnerId() != curentUser.getId()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        CosmeticDTO cosmetic = catService.disapplyCosmetic(catId, cosmId, curentUser);
        if (cosmetic == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cosmetic, HttpStatus.OK);
    }

    @GetMapping("/stat")
    public ResponseEntity getStat(@RequestParam Integer catId) {
        if(!catService.existCat(catId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User curentUser = (User) authentication.getPrincipal();
        Cat cat = catService.getCat(catId);
        User catOwner = userService.getUser(cat.getOwnerId());
        if (catOwner.getId() != curentUser.getId() && !friendsService.isFriends(catOwner, curentUser)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(catService.getStat(catId), HttpStatus.OK);
    }

    @PostMapping("/food")
    public ResponseEntity toFeed(@RequestParam Integer catId) {
        if(!catService.existCat(catId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User curentUser = (User) authentication.getPrincipal();
        Cat cat = catService.getCat(catId);
        User catOwner = userService.getUser(cat.getOwnerId());
        if (catOwner.getId() != curentUser.getId() && !friendsService.isFriends(catOwner, curentUser)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(catService.toFeedCat(catId), HttpStatus.OK);
    }

    @PostMapping("/petting")
    public ResponseEntity toPet(@RequestParam Integer catId) {
        if(!catService.existCat(catId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User curentUser = (User) authentication.getPrincipal();
        Cat cat = catService.getCat(catId);
        User catOwner = userService.getUser(cat.getOwnerId());
        if (catOwner.getId() != curentUser.getId() && !friendsService.isFriends(catOwner, curentUser)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(catService.toPetCat(catId), HttpStatus.OK);
    }
}

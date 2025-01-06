package org.example.kotiki.infrastructure.web;

import org.example.kotiki.infrastructure.dto.ErrorMessageDTO;
import org.example.kotiki.infrastructure.dto.UserRegistrationDTO;
import org.example.kotiki.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;


    @PostMapping()
    public ResponseEntity addUser(@RequestBody UserRegistrationDTO request) {
        try{
            userService.saveUserWithDefaults(request);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
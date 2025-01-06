package org.example.kotiki.infrastructure.web;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class SessionController {

    @GetMapping("/")
    public String mainPage() {
        return "Добро пожпловать в котячий клуб! Хотите зарегистрироваться, или у вас уже есть аккаунт?"; }

    @GetMapping("/home")
    public String home() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return "Welcome, " + currentPrincipalName; // Приветственное сообщение с именем пользователя
    }
    @GetMapping("/logout-success")
    public String logout() {
        return "You have been logged out!"; // Сообщение о выходе
    }
    
}
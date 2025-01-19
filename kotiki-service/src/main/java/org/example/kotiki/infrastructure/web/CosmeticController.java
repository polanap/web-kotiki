package org.example.kotiki.infrastructure.web;

import jakarta.servlet.http.HttpServletResponse;
import org.example.kotiki.application.cosmetic.CosmeticType;
import org.example.kotiki.infrastructure.dto.CosmeticsDTO;
import org.example.kotiki.infrastructure.service.CosmeticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/cosmetics")
public class CosmeticController {
    @Autowired
    CosmeticService cosmeticService;
    @GetMapping()
    public ResponseEntity home() {
        CosmeticsDTO responseDTO = new CosmeticsDTO();
        responseDTO.setCosmetics(CosmeticType.values());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}

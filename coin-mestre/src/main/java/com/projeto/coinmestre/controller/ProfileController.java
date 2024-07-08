package com.projeto.coinmestre.controller;

import com.projeto.coinmestre.dto.res.UserResDTO;
import com.projeto.coinmestre.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping(value = "/profile")
public class ProfileController {

    private UserService userService;

    @GetMapping
    public UserResDTO findByLoggedUser() {
        return this.userService.findByLoggedUser();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public UserResDTO me() {
        return this.userService.findByLoggedUser();
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(value = "/logout")
    public void logout() {
        this.userService.logout();
    }

}

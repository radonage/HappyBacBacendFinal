package com.happyBackLast.happyBacklast.controller;

import com.happyBackLast.happyBacklast.DTO.LoginRequest;
import com.happyBackLast.happyBacklast.DTO.RegisterRequest;
import com.happyBackLast.happyBacklast.model.User;
import com.happyBackLast.happyBacklast.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest req) {
        return authService.register(req);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

}
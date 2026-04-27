package com.happyBackLast.happyBacklast.controller;

import com.happyBackLast.happyBacklast.DTO.LoginRequest;
import com.happyBackLast.happyBacklast.DTO.RegisterRequest;
import com.happyBackLast.happyBacklast.model.PasswordResetToken;
import com.happyBackLast.happyBacklast.model.User;
import com.happyBackLast.happyBacklast.service.AuthService;
import com.happyBackLast.happyBacklast.service.serviceImpl.PasswordResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;
    private PasswordResetService resetService;

    public AuthController(AuthService authService, PasswordResetService resetService) {
        this.authService = authService;
        this.resetService = resetService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest req) {
        return authService.register(req);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(authService.login(request));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        resetService.createResetToken(email);
        return ResponseEntity.ok("Email sent");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @RequestParam String token,
            @RequestParam String password) {

        resetService.resetPassword(token, password);
        return ResponseEntity.ok("Password updated");
    }
}
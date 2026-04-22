package com.happyBackLast.happyBacklast.service;

import com.happyBackLast.happyBacklast.DTO.AuthResponse;
import com.happyBackLast.happyBacklast.DTO.LoginRequest;
import com.happyBackLast.happyBacklast.DTO.RegisterRequest;
import com.happyBackLast.happyBacklast.model.User;

public interface AuthService {

    User register(RegisterRequest request);
    AuthResponse login(LoginRequest request);

}
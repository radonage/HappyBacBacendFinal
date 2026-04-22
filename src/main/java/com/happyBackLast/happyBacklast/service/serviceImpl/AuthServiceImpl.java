package com.happyBackLast.happyBacklast.service.serviceImpl;

import com.happyBackLast.happyBacklast.DTO.AuthResponse;
import com.happyBackLast.happyBacklast.DTO.LoginRequest;
import com.happyBackLast.happyBacklast.DTO.RegisterRequest;
import com.happyBackLast.happyBacklast.model.*;
import com.happyBackLast.happyBacklast.repository.UserRepository;
import com.happyBackLast.happyBacklast.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Country country = new Country();
        country.setId(request.getCountryId());
        user.setCountry(country);

        Filiere filiere = new Filiere();
        filiere.setId(request.getFiliereId());
        user.setFiliere(filiere);

        Level level = new Level();
        level.setId(request.getLevelId());
        user.setLevel(level);

        return userRepository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        AuthResponse res = new AuthResponse();
        res.setId(user.getId());
        res.setEmail(user.getEmail());
        res.setPhone(user.getPhone());
        res.setCountryId(user.getCountry().getId());
        res.setFiliereId(user.getFiliere().getId());
        res.setLevelId(user.getLevel().getId());

        return res;
    }
}
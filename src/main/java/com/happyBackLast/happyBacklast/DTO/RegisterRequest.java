package com.happyBackLast.happyBacklast.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String email;
    private String phone;
    private String password;
    private String firstName;
    private String lastName;
    private Long countryId;
    private Long filiereId;
    private Long levelId;
}
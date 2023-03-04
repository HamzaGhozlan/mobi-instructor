package com.psut.model.shared;

import lombok.Data;

@Data
public abstract class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String username;
    private String password;
    private UserStatus status;
}

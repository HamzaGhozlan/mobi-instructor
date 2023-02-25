package com.psut.models.shared;

import com.psut.models.shared.Gender;
import lombok.Data;

@Data
public abstract class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String username;
    private String password;
}

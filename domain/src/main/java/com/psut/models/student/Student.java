package com.psut.models.student;

import com.psut.models.shared.User;
import lombok.Data;

@Data
public class Student extends User {
    private String email;
}

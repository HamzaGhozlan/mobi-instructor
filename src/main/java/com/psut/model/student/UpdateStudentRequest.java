package com.psut.model.student;

import lombok.Data;

@Data
public class UpdateStudentRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    public void applyUpdatesOn(Student student) {
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setUsername(username);
        student.setPassword(password);
    }
}

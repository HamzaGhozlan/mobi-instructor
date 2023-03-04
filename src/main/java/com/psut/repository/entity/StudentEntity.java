package com.psut.repository.entity;

import com.psut.model.shared.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "student")
public class StudentEntity extends User {
}

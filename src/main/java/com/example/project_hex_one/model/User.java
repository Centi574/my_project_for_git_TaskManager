package com.example.project_hex_one.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

import static jakarta.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(unique = true, name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @CreationTimestamp
    @Temporal(TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    public User(long id) {
        this.id = id;
    }
}

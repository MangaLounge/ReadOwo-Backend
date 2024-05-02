package com.readowo.api.ReadOwo.Models;

import com.readowo.api.publishing.Models.Chapters;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id ;

    private String Name;
    private String email;

    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<UserProfile> userProfileSet = new HashSet<>();
}

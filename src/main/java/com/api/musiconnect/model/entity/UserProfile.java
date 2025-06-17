package com.api.musiconnect.model.entity;

import com.api.musiconnect.model.enums.Language;
import com.api.musiconnect.model.enums.UserGender;
import com.api.musiconnect.model.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "users_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private LocalDate birthDate;

    private String firstName;

    private String lastName;

    private String bio;

    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserGender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Language language;
}

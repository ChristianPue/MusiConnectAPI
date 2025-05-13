package com.api.musiconnect.model.entity;

import com.api.musiconnect.model.enums.Gender;
import com.api.musiconnect.model.enums.Instrument;
import com.api.musiconnect.model.enums.UserStatus;
import com.api.musiconnect.model.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints =
        {
                @UniqueConstraint(columnNames = "email", name = "uk_user_email"),
                @UniqueConstraint(columnNames = "username", name = "uk_user_username")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UserType userType; // Tipo de Usuario: Admin, Artist, Producer

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    private Gender gender;

    private String bio;
    private String location;

    @Column(nullable = false)
    private UserStatus status; // Disponibilidad: Online, Away, Do Not Disturb, Invisible

    @ElementCollection(targetClass = Instrument.class)
    @CollectionTable(name = "user_instruments", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "instrument")
    private List<Instrument> instruments; // Lista de instrumentos del usuario

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // Fecha de creación

    private LocalDateTime updatedAt; // Fecha de última actualización
}

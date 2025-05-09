package com.api.musiconnect.model.entity;

import com.api.musiconnect.model.enums.BandStatus;
import com.api.musiconnect.model.enums.Gender;
import com.api.musiconnect.model.enums.Instrument;
import com.api.musiconnect.model.enums.MusicalGenre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bandas", uniqueConstraints = {
        @UniqueConstraint(columnNames = "nombre", name = "uk_banda_nombre")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Band {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bandId;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BandStatus estado;

    @ElementCollection(targetClass = MusicalGenre.class)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private List<MusicalGenre> musicalGenre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;

}

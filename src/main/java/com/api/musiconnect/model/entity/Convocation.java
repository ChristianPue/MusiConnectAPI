package com.api.musiconnect.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "convocations")
public class Convocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer convocatoriaId;

    @Column(nullable = false)
    private String titulo;

    private String descripcion;

    private LocalDateTime fecha;

    private String tipo;
}

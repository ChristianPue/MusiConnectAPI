package com.api.musiconnect.model.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Convocatori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creador;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public User getCreador() {
        return creador;
    }

    public void setCreador(User creador) {
        this.creador = creador;
    }

   
}


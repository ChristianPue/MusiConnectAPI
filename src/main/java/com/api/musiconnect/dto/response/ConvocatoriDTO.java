package com.api.musiconnect.dto.response;

import java.time.LocalDate;

public class ConvocatoriDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDate fecha;
    private String creadorNombre;
    private int cantidadFavoritos;

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

    public String getCreadorNombre() {
        return creadorNombre;
    }

    public void setCreadorNombre(String creadorNombre) {
        this.creadorNombre = creadorNombre;
    }

    public int getCantidadFavoritos() {
        return cantidadFavoritos;
    }

    public void setCantidadFavoritos(int cantidadFavoritos) {
        this.cantidadFavoritos = cantidadFavoritos;
    }
}


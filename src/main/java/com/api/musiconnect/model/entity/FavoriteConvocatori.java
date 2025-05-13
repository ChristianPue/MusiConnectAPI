package com.api.musiconnect.model.entity;

import jakarta.persistence.*;

@Entity
public class FavoriteConvocatori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "convocatori_id")
    private Convocatori convocatori;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Convocatori getConvocatori() {
        return convocatori;
    }

    public void setConvocatori(Convocatori convocatori) {
        this.convocatori = convocatori;
    }
}
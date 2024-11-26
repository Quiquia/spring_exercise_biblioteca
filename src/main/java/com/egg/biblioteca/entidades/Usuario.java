package com.egg.biblioteca.entidades;

import java.util.UUID;

import com.egg.biblioteca.enumeraciones.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id-usuario")
    private UUID idUsuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email", unique = true, nullable =  false)
    private String email;

    @Column(name = "password")
    private String password;

    //roll definido como enum
    @Enumerated(EnumType.STRING)
    private Rol rol;

    public Usuario() {

    }
    
    // Getters y Setters

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    
}

package com.egg.biblioteca.entidades;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "autor" )
public class Autor {
    @Id
    @Column(name = "id-autor")
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id;

    @Column(name = "nombre")
    private String nombre;

    public Autor() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    

    
    
}

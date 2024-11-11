package com.egg.biblioteca.entidades;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "libro" )
public class Libro {

    @Id
    @Column(name = "isbn", length = 13)
    private Long isbn;

    //eliminación no permanente poner ACTIVATE
    
    @Column(name = "titulo")
    private String titulo;

    @Column(name = "ejemplares")
    private String ejemplares;

    @Column(name = "alta")
    @Temporal(TemporalType.DATE)
    private Date alta;

    @ManyToOne
    @JoinColumn(name = "id-autor")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "id-editorial")
    private Editorial editorial;

    
    public Libro() {
        
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long id) {
        this.isbn = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(String ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }



    
    
}

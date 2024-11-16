package com.egg.biblioteca.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorios.AutorRepositorio;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;


    @Transactional
    public void crearAutor(String nombre) throws Exception {
        validar(nombre);//valida el nombre que no este vacio ni null

        Autor autor = new Autor(); // Instancio un objeto del tipo Autor
        autor.setNombre(nombre);// Seteo el atributo, con el valor recibido como parámetr

        autorRepositorio.save(autor);

    }
    
    @Transactional
    public List<Autor> listarAutores() {

        List<Autor> autores = new ArrayList<>();
        autores = autorRepositorio.findAll();
        return autores;
    }
    
    //modificar autor
    @Transactional
    public void modificarAutor(String nombre, UUID id) throws Exception {
        validar(nombre);
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();

            autor.setNombre(nombre);
            autorRepositorio.save(autor);
        }

    }
    //busca un elemento especifico
    @Transactional(readOnly = true) //indica que el metodo es de solo lectura
    public Autor getOne(UUID id) {
        return autorRepositorio.getReferenceById(id);   
    }

    public void validar(String nombre) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
             throw new MiException("el nombre no puede ser nulo o estar vacío");
        }
    }
    
}

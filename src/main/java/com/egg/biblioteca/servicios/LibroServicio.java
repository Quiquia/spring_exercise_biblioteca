package com.egg.biblioteca.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import jakarta.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libro;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorios.AutorRepositorio;
import com.egg.biblioteca.repositorios.EditorialRepositorio;
import com.egg.biblioteca.repositorios.LibroRepositorio;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Autowired
    private EditorialRepositorio editorialRepositorio;


    @Transactional
    public void crearLibro(String titulo, Long isbn, String ejemplares, 
            UUID idAutor, UUID idEditorial) throws MiException {
        validar(titulo, isbn, ejemplares, idAutor, idEditorial);


        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();

        // Crear el nuevo libro y setear sus atributos
        Libro libro = new Libro();

        libro.setTitulo(titulo);
        libro.setIsbn(isbn);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date()); // Establece la fecha de alta con la fecha actual
        libro.setAutor(autor);
        libro.setEditorial(editorial);

        // Guardar el libro en el repositorio
        libroRepositorio.save(libro);

    }
    
    //recuperar datos
    @Transactional
    public List<Libro> listarLibros() {
        List<Libro> libros = new ArrayList<>();
        libros = libroRepositorio.findAll();
        return libros;

    }
    

     @Transactional
     public void modificarLibro(String titulo, Long isbn, String ejemplares, 
             UUID idAutor, UUID idEditorial) throws MiException {

        validar(titulo, isbn, ejemplares, idAutor, idEditorial);
         // es un objeto contenedor que retorna
         Optional<Libro> respuesta = libroRepositorio.findById(isbn);
         Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
         Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);

         Autor autor = new Autor();
         Editorial editorial = new Editorial();

         if (respuestaAutor.isPresent()) {
             autor = respuestaAutor.get();
         }

         if (respuestaEditorial.isPresent()) {
             editorial = respuestaEditorial.get();
         }

         if (respuesta.isPresent()) {
             // si existe respuesta recien se puede modificar
             Libro libro = respuesta.get();

             libro.setAutor(autor);
             libro.setEditorial(editorial);
             libro.setTitulo(titulo);
             libro.setIsbn(isbn);
             libro.setEjemplares(ejemplares);

         }
     }
     
     private void validar(String titulo, Long isbn, String ejemplares, 
             UUID idAutor, UUID idEditorial) throws MiException { 
        
         if (titulo.isEmpty() || titulo == null) {
             throw new MiException("el titulo no puede ser nulo o estar vacio.");
         }
        
         if (isbn == null) {
             throw new MiException("el isbn no puede ser nulo.");

         }

         if (ejemplares.isEmpty() || ejemplares == null) {
             throw new MiException("los ejemplares no puede ser nulo o estar vacio.");
         }
         
         if (idAutor == null) {
             throw new MiException("el Autor no puede ser nulo.");
         }
         
         if(idEditorial == null){
             throw new MiException("la editorial no puede ser nulo.");
         }

     }
    
}

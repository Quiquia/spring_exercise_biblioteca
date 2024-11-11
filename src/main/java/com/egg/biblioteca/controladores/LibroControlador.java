package com.egg.biblioteca.controladores;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.servicios.AutorServicio;
import com.egg.biblioteca.servicios.EditorialServicio;
import com.egg.biblioteca.servicios.LibroServicio;

@Controller
@RequestMapping("/libro") // localhost:8080/libro
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar") //localhost:8080/libro/registrar
    public String registrar() {
        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbn, @RequestParam String titulo,
            @RequestParam(required = false) String ejemplares, @RequestParam UUID idAutor,
            @RequestParam UUID idEditorial, ModelMap modelo) {
        
                try {
                    
                    libroServicio.crearLibro(titulo, isbn, ejemplares, idAutor, idEditorial);
                   modelo.put("exito", "El Autor fue registrado correctamente!");

                } catch (MiException ex) {
                     modelo.put("error", ex.getMessage());

                    return "libro_form.html"; // volvemos a cargar el formulario.
                }
                
                 return "index.html";


    }





    
}

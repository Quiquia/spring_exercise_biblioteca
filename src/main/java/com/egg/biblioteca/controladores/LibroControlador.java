package com.egg.biblioteca.controladores;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libro;
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
    public String registrar(ModelMap modelo) {
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);
        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbn, @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String ejemplares, @RequestParam(required =  false) UUID idAutor,
            @RequestParam(required = false) UUID idEditorial, ModelMap modelo)  throws Exception{
        
                try {
                    
                    libroServicio.crearLibro(titulo, isbn, ejemplares, idAutor, idEditorial);

                    modelo.put("exito", "El libro fue registrado correctamente!");

                } catch (MiException ex) {
                    List<Autor> autores = autorServicio.listarAutores();
                    List<Editorial> editoriales = editorialServicio.listarEditoriales();
                    modelo.addAttribute("autores", autores);
                    modelo.addAttribute("editoriales", editoriales);
                    modelo.put("error", ex.getMessage());
                    return "libro_form.html"; // volvemos a cargar el formulario.
                }
                
                 return "index.html";


    }


     @GetMapping("/lista")
    public String listar(ModelMap modelo) {

       List<Libro> libros = libroServicio.listarLibros();
       modelo.addAttribute("libros", libros);
       return "libro_list.html";
   }



    
}

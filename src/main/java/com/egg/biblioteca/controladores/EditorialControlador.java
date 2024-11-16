package com.egg.biblioteca.controladores;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.servicios.EditorialServicio;

@Controller
@RequestMapping("/editorial") // localhost:8080/editorial
public class EditorialControlador {
    
     @Autowired
    private EditorialServicio editorialServicio;

     @GetMapping("/registrar") // localhost:8080/editorial/registrar
     public String registrar() {
         return "editorial_form.html";  
     }
    
    
     @PostMapping("/registro")
     public String registro(@RequestParam String nombre, ModelMap modelo) throws Exception {

         try {
             editorialServicio.crearEditorial(nombre);

             modelo.put("exito", "La Editorial fue registrada correctamente!");
         } catch (MiException ex) {

             modelo.put("error", ex.getMessage());
             return "editorial_form.html";
         }

         return "index.html";
     }
    
     @GetMapping("/lista")
     public String listar(ModelMap modelo) {
         List<Editorial> editoriales = editorialServicio.listarEditoriales();
         modelo.addAttribute("editoriales", editoriales);
         //  System.out.print("Tu mensaje aquí");
         // System.out.print(editoriales.toString());
         return "editorial_list.html";
         //  return "editorial_form.html";  

     }

     @GetMapping("/modificar/{id}")
     public String modificar(@PathVariable UUID id, ModelMap modelo) {

         modelo.put("editorial", editorialServicio.getOne(id));
         return "editorial_modificar.html";
     }
     
     @PostMapping("/modificar/{id}")
     public String modificar(@PathVariable UUID id, String nombre, ModelMap modelo) throws Exception {
        
         try {
            
            editorialServicio.modificarEditorial(nombre, id);
            return "redirect:../lista";
                   
        } catch (MiException ex) {
             modelo.put("error", ex.getMessage());
             return "editorial_modificar.html";
        }
     }

}

package com.Project.Project.Controladores;

import com.Project.Project.Dao.Service.IContactoService;
import com.Project.Project.Modelo.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ContactoControlador {
    @Autowired
    private IContactoService contactoService;

    private Contacto contacto;

    @GetMapping("/contactos")
    public List<Contacto> getAll(){
        return contactoService.findAll();
    }
    @GetMapping("/contactos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Contacto> listarPorId(@PathVariable Long id){
        return contactoService.findById(id);
    }

    @PostMapping("/contactos")
    @ResponseStatus(HttpStatus.CREATED)
    public Contacto create(@RequestBody Contacto contacto){
        return contactoService.save(contacto);
    }
}

package com.Project.Project.Controladores;

import com.Project.Project.Modelo.Sede;
import com.Project.Project.Dao.Repository.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SedeController {

    @Autowired
    private SedeRepository sedeRepository;

    @GetMapping("/sedes")
    public String listarSedes(Model modelo) {
        List<Sede> listaSedes = sedeRepository. findAll();
        modelo.addAttribute("listaSedes", listaSedes);
        return "sedes";
    }

    @GetMapping("/sedes/nuevo")
    public String mostrarFormularioNuevaSede(Model modelo){
        modelo.addAttribute("sede", new Sede());
        return "addSede";
    }

    @PostMapping("/sedes/guardar")
    public String guardarSede(Sede sede) {
        sedeRepository.save(sede);
        return "redirect:/sedes";
    }
}

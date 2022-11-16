package com.Project.Project.Controladores;

import com.Project.Project.Modelo.Sede;
import com.Project.Project.Dao.Repository.SedeRepository;
import com.Project.Project.Modelo.Trabajador;
import com.Project.Project.Dao.Repository.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TrabajadorController {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private SedeRepository sedeRepository;

    @GetMapping("trabajadores/nuevo")
    public String mostrarFormularioNuevoTrabajador(Model modelo){
        List<Sede> listaSedes = sedeRepository.findAll();

        modelo.addAttribute("trabajador", new Trabajador());
        modelo.addAttribute("listaSedes", listaSedes);

        return "addTrabajador";
    }

    @PostMapping("/trabajadores/guardar")
    public String guardarTrabajador(Trabajador trabajador) {
        trabajadorRepository.save(trabajador);
        return "redirect:/trabajadores";
    }

    @GetMapping("/trabajadores")
    public String listarTrabajadores(Model modelo) {
        List<Trabajador> listaTrabajadores = trabajadorRepository.findAll();
        modelo.addAttribute("listaTrabajadores", listaTrabajadores);
        return "trabajadores";
    }
}

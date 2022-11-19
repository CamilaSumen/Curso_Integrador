package com.Project.Project.Controladores;

import com.Project.Project.Modelo.Sede;
import com.Project.Project.Dao.Repository.SedeRepository;
import com.Project.Project.Modelo.Trabajador;
import com.Project.Project.Dao.Repository.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TrabajadorController {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private SedeRepository sedeRepository;

    @GetMapping("/admin/trabajadores/nuevo")
    public String mostrarFormularioNuevoTrabajador(Model modelo){
        List<Sede> listaSedes = sedeRepository.findAll();

        modelo.addAttribute("trabajador", new Trabajador());
        modelo.addAttribute("listaSedes", listaSedes);

        return "administrador/addTrabajador";
    }

    @PostMapping("/admin/trabajadores/guardar")
    public String guardarTrabajador(Trabajador trabajador) {
        trabajadorRepository.save(trabajador);
        return "redirect:/admin/trabajadores";
    }

    @GetMapping("/admin/trabajadores")
    public String listarTrabajadores(Model modelo) {
        List<Trabajador> listaTrabajadores = trabajadorRepository.findAll();
        modelo.addAttribute("listaTrabajadores", listaTrabajadores);
        return "trabajadores";
    }

    @GetMapping("/admin/trabajadores/editar/{id}")
    public String mostrarFormularioModificarTrabajador(@PathVariable("id") Integer id, Model modelo) {
        Trabajador trabajador = trabajadorRepository.findById(id).get();
        modelo.addAttribute("trabajador", trabajador);

        List<Sede> listaSedes = sedeRepository.findAll();
        modelo.addAttribute("listaSedes", listaSedes);

        return "administrador/addTrabajador";
    }

    @GetMapping("/admin/trabajadores/eliminar/{id}")
    public String eliminarTrabajador (@PathVariable("id") Integer id, Model modelo) {
        trabajadorRepository.deleteById(id);
        return "redirect:/admin/trabajadores";
    }
}


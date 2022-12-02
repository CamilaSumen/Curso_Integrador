package com.Project.Project.Controladores;

import com.Project.Project.Dao.Repository.SedeRepository;
import com.Project.Project.Dao.Repository.TrabajadorRepository;
import com.Project.Project.Dao.Service.SedeService;
import com.Project.Project.Dao.Service.TrabajadorService;
import com.Project.Project.Modelo.Sede;
import com.Project.Project.Modelo.Trabajador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TrabajadorController {

    private final Logger log = LoggerFactory.getLogger(TrabajadorController.class);

    @Autowired
    private TrabajadorService trabajadorService;

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private SedeService sedeService;

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
    public String edit(@PathVariable Integer id, Model model){
        Trabajador trabajador = trabajadorRepository.findById(id).get();
        model.addAttribute("trabajador", trabajador);
        List<Sede> listaSedes = sedeRepository.findAll();
        model.addAttribute("listaSedes", listaSedes);
        return "administrador/editTrabajador";
    }

    @PostMapping("/admin/trabajadores/update")
    public String update(Trabajador trabajador) {
        Trabajador t = new Trabajador();
        t = trabajadorService.get(trabajador.getId()).get();

        trabajadorService.update(trabajador);
        return "redirect:/admin/trabajadores";
    }

    @GetMapping("/admin/trabajadores/eliminar/{id}")
    public String eliminarTrabajador (@PathVariable("id") Integer id, Model modelo) {
        trabajadorRepository.deleteById(id);
        return "redirect:/admin/trabajadores";
    }

}


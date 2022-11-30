package com.Project.Project.Controladores;

import com.Project.Project.Dao.Repository.SedeRepository;
import com.Project.Project.Dao.Service.SedeService;
import com.Project.Project.Modelo.Sede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SedeController {

    @Autowired
    private SedeService sedeService;
    @Autowired
    private SedeRepository sedeRepository;

    @GetMapping("/admin/sedes")
    public String listarSedes(Model modelo) {
        List<Sede> listaSedes = sedeRepository. findAll();
        modelo.addAttribute("listaSedes", listaSedes);
        return "administrador/sedes";
    }

    @GetMapping("/admin/sedes/nuevo")
    public String mostrarFormularioNuevaSede(Model modelo){
        modelo.addAttribute("sede", new Sede());
        return "administrador/addSede";
    }

    @PostMapping("/admin/sedes/guardar")
    public String guardarSede(Sede sede) {
        sedeRepository.save(sede);
        return "redirect:/admin/sedes";
    }

    @GetMapping("/admin/sedes/editar/{id}")
    /*public String mostrarFormularioModificarSede(@PathVariable("id") Integer id, Model modelo) {
        Sede sede = sedeRepository.findById(id).get();
        modelo.addAttribute("sede", sede);

        return "administrador/addSede";
    }*/
    public String edit(@PathVariable Integer id, Model model){
        Sede sede = sedeRepository.findById(id).get();
        model.addAttribute("sede", sede);
        List<Sede> listaSedes = sedeRepository.findAll();
        model.addAttribute("listaSedes", listaSedes);
        return "administrador/editSede";
    }

    @PostMapping("/admin/sedes/update")
    public String update(Sede sede) {
        Sede s = new Sede();
        s = sedeService.get(sede.getId()).get();

        sedeService.update(sede);
        return "redirect:/admin/sedes";
    }
    @GetMapping("/admin/sedes/eliminar/{id}")
    public String eliminarSede (@PathVariable("id") Integer id, Model modelo) {
        sedeRepository.deleteById(id);
        return "redirect:/admin/sedes";
    }

}

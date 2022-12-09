package com.Project.Project.Controladores;

import com.Project.Project.Dao.Service.IOrdenService;
import com.Project.Project.Dao.Service.ProductoService;
import com.Project.Project.Modelo.Orden;
import com.Project.Project.Modelo.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AdmController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private IOrdenService ordenService;

    private Logger log = LoggerFactory.getLogger(AdmController.class);

    @GetMapping("admin/administrativo")
    public String verPaginaInicio(){
        return "administrador/administrativo";
    }

    @GetMapping("/administrativo/carta")
    public String verCarta(Model model){
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        return "productos/carritoprueba";
    }

    @GetMapping("/administrativo/ordenes")
    public String ordenes(Model model){
        model.addAttribute("ordenes", ordenService.findAll());
        return "administrador/ordenes";
    }
    @GetMapping("/administrativo/detalle/{id}")
    public String detalle(Model model, @PathVariable Integer id){
        log.info("Id de la orden", id);
        Orden orden = ordenService.findById(id).get();
        model.addAttribute("detalles", orden.getDetalle());
        return "administrador/detalleorden";
    }
}

package com.Project.Project.Controladores;

import com.Project.Project.Dao.Service.ProductoService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductoService productoService;

    @GetMapping("")
    public String home(){
        return "usuario/index";
    }

    @GetMapping("/carta")
    public String carta1(){
        return "usuario/nuestraCarta";
    }

    @GetMapping("/promociones")
    public String promocion(){
        return "usuario/promociones";
    }

    @GetMapping("/restaurantes")
    public String restaurante(){
        return "usuario/restaurantes";
    }

    @GetMapping("/login")
    public String iniciar(){
        return "usuario/logIn";
    }

    @GetMapping("/registrar")
    public String registro(){
        return "usuario/register";
    }

    @GetMapping("/reservar")
    public String reservar(){
        return "usuario/reserva";
    }

    @GetMapping("/contactar")
    public String contacto(){
        return "usuario/escribenos";
    }

    @GetMapping("/carrito")
    public String vercarta(Model model){
        model.addAttribute("productos", productoService.findAll());
        return "usuario/carrito";
    }
    @GetMapping("/verproducto/{id}")
    public String verproducto(@PathVariable Integer id){
        log.info("Id producto enviado como parámetro {}", id);
        return "usuario/verproducto";
    }

}

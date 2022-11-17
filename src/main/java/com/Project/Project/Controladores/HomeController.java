package com.Project.Project.Controladores;

import com.Project.Project.Dao.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

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


    @GetMapping("/carrito")
    public String vercarta(Model model){
        model.addAttribute("productos", productoService.findAll());
        return "usuario/carrito";
    }

}

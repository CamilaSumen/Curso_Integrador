package com.Project.Project.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdmController {
    @GetMapping("/administracion")
    public String verPaginaInicio(){
        return "administrador/administrativo";
    }
}

package com.Project.Project.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/administracion")
    public String verPaginaInicio(){
        return "administrativo.html";
    }
}

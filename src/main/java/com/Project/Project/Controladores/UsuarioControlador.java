package com.Project.Project.Controladores;

import com.Project.Project.Dao.Service.IUsuarioService;
import com.Project.Project.Modelo.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UsuarioControlador {

    private final Logger logger = LoggerFactory.getLogger(UsuarioControlador.class);

    @Autowired
    private IUsuarioService usuarioService;


    @GetMapping("/registrar")
    public String create(){
        return "usuario/register";
    }

    @PostMapping("/save")
    public String save(Usuario usuario){
        logger.info("Usuario registro: {}", usuario);
        usuario.setTipo("USER");
        usuarioService.save(usuario);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "usuario/logIn";
    }
    @PostMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session){
        logger.info("Accesos: {}", usuario);

        Optional<Usuario> user = usuarioService.findByEmail(usuario.getEmail());
        logger.info("Usuario de db: {}", user.get());

        //Login de administrador y usuario

        if(user.isPresent()){
            session.setAttribute("idusuario", user.get().getId());
            if(user.get().getTipo().equals("ADMIN")){
                return "redirect:/admin/administrativo";
            } else {
                return "redirect:/carta";
            }
            } else{
            logger.info("Usuario no existe");
        }
        return "redirect:/carta";
    }


}

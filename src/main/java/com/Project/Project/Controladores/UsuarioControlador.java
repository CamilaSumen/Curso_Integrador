package com.Project.Project.Controladores;

import com.Project.Project.Dao.Service.IOrdenService;
import com.Project.Project.Dao.Service.IUsuarioService;
import com.Project.Project.Modelo.Orden;
import com.Project.Project.Modelo.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class UsuarioControlador {

    private final Logger logger = LoggerFactory.getLogger(UsuarioControlador.class);

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IOrdenService ordenService;


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

    @GetMapping("/compras")
    public String obtenerCompras(HttpSession session, Model model){
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        List<Orden> ordenes = ordenService.findByUsuario(usuario);
        model.addAttribute("ordenes", ordenes);
        return "usuario/compras";
    }

    @GetMapping("/detalle/{id}")
    //Path para mapear el parámetro
    public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model){
        logger.info("Id de la orden: {}", id);
        //detalles de la orden
        Optional<Orden> orden = ordenService.findById(id);
        model.addAttribute("detalles", orden.get().getDetalle());
        //session
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "usuario/detallecompra";
    }

    @GetMapping("/cerrar")
    public String cerrarSesion(HttpSession session){
        session.removeAttribute("idusuario");
        return "redirect:/login";
    }


}

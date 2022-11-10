package com.Project.Project.Controladores;

import com.Project.Project.Dao.UsuarioDao;
import com.Project.Project.Modelo.Usuario;
import com.Project.Project.util.JWTutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControlador {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTutil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {
        System.out.println("Usuario existe " + usuarioDao.obtenerUsuarioPorCredenciales(usuario));
        if(usuarioDao.obtenerUsuarioPorCredenciales(usuario)){
            return "OK";
        }
        return "FAIL";
    }
}

package com.Project.Project.Dao.Service;

import com.Project.Project.Modelo.Orden;
import com.Project.Project.Modelo.Usuario;

import java.util.List;

public interface IOrdenService {

    List<Orden> findAll();
    Orden save (Orden orden);
    String generarNumeroOrden();
    List<Orden> findByUsuario (Usuario usuario);
}

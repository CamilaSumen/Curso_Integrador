package com.Project.Project.Dao.Service;

import com.Project.Project.Modelo.Orden;

import java.util.List;

public interface IOrdenService {

    List<Orden> findAll();
    Orden save (Orden orden);
    String generarNumeroOrden();
}

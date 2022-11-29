package com.Project.Project.Dao.Service;


import com.Project.Project.Modelo.Trabajador;

import java.util.Optional;

public interface TrabajadorService {

    public Optional<Trabajador> get(Integer id);
    public void update(Trabajador trabajador);
}

package com.Project.Project.Dao.Service;


import com.Project.Project.Modelo.Sede;

import java.util.Optional;

public interface SedeService {
    public Optional<Sede> get(Integer id);
    public void update(Sede sede);
}

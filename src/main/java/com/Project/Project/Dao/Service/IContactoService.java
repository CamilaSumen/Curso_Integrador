package com.Project.Project.Dao.Service;

import com.Project.Project.Modelo.Contacto;

import java.util.List;
import java.util.Optional;

public interface IContactoService {
    public List<Contacto> findAll();
    public Optional<Contacto> findById(Long id);
    public Contacto save(Contacto contacto);
}

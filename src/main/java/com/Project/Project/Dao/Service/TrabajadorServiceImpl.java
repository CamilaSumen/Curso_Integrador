package com.Project.Project.Dao.Service;


import com.Project.Project.Dao.Repository.TrabajadorRepository;
import com.Project.Project.Modelo.Trabajador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrabajadorServiceImpl implements TrabajadorService{

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Override
    public Optional<Trabajador> get(Integer id) {
        return trabajadorRepository.findById(id);
    }

    @Override
    public void update(Trabajador trabajador) {
        trabajadorRepository.save(trabajador);
    }
}

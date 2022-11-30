package com.Project.Project.Dao.Service;

import com.Project.Project.Dao.Repository.SedeRepository;
import com.Project.Project.Modelo.Sede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SedeServiceImpl implements SedeService{
    @Autowired
    private SedeRepository sedeRepository;

    @Override
    public Optional<Sede> get(Integer id) {
        return sedeRepository.findById(id);
    }

    @Override
    public void update(Sede sede) {
        sedeRepository.save(sede);
    }
}

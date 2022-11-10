package com.Project.Project.Dao.Service;

import com.Project.Project.Modelo.Reserva;

import java.util.List;
import java.util.Optional;

public interface IReservaService {
    public List<Reserva> findAll();
    public Optional<Reserva> findById(Long id);
    public Reserva save(Reserva reserva);
}

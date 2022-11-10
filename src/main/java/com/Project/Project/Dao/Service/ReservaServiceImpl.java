package com.Project.Project.Dao.Service;

import com.Project.Project.Dao.IReservaDao;
import com.Project.Project.Modelo.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements IReservaService {

    @Autowired
    private IReservaDao reservaDAO;

    @Override
    public List<Reserva> findAll(){
        return reservaDAO.findAll();
    }

    @Override
    public Optional<Reserva> findById(Long id){
        return reservaDAO.findById(id);
    }

    @Override
    public Reserva save(Reserva reserva){
        reservaDAO.save(reserva);
        return reserva;
    }
}

package com.Project.Project.Controladores;

import com.Project.Project.Dao.Service.IReservaService;
import com.Project.Project.Modelo.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReservaControlador {
    @Autowired
    private IReservaService reservaService;

    private Reserva reserva;

    @GetMapping("/reservas")
    public List<Reserva> getAll(){
        return reservaService.findAll();
    }


    @GetMapping("/reservas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Reserva> listarPorId(@PathVariable Long id){
        return reservaService.findById(id);
    }


    @PostMapping("/reservas")
    @ResponseStatus(HttpStatus.CREATED)
    public  Reserva create(@RequestBody Reserva reserva){
        return reservaService.save(reserva);
    }
}

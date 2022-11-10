package com.Project.Project.Dao.Service;

import com.Project.Project.Dao.IContactoDao;
import com.Project.Project.Modelo.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactoServiceImpl implements IContactoService {
    @Autowired
    private IContactoDao contactoDAO;

    @Override
    public List<Contacto> findAll(){
        return contactoDAO.findAll();
    }

    @Override
    public Optional<Contacto> findById(Long id){
        return contactoDAO.findById(id);
    }

    @Override
    public Contacto save(Contacto contacto){
        contactoDAO.save(contacto);
        return contacto;
    }
}

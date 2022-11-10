package com.Project.Project.Dao;

import com.Project.Project.Modelo.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContactoDao extends JpaRepository<Contacto, Long> {
}

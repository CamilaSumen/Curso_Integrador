package com.Project.Project.Dao.Service;

import com.Project.Project.Modelo.Usuario;

import java.util.Optional;


public interface IUsuarioService {

    Optional <Usuario> findById(Integer id);

    Usuario save (Usuario usuario);

    Optional<Usuario> findByEmail(String email);

}

package com.Project.Project.Dao;

import com.Project.Project.Modelo.Usuario;

import java.util.List;

    public interface UsuarioDao{

    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrar(Usuario usuario);

    boolean obtenerUsuarioPorCredenciales(Usuario usuario);

    }
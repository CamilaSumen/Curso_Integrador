package com.Project.Project.Modelo;

import javax.persistence.*;

@Entity
public class Sede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false, unique = true)
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Sede() {
        super();
    }

    public Sede(Integer id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
    }

    public Sede(Integer id) {
        super();
        this.id = id;
    }

    public Sede(String nombre) {
        super();
        this.nombre = nombre;
    }

}

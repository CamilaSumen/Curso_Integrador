package com.Project.Project.Modelo;

import javax.persistence.*;

@Entity
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 8, nullable = false, unique = true)
    private String dni;

    @Column(length = 150, nullable = false)
    private String nombre;

    @Column(length = 9, nullable = false)
    private String telefono;

    @Column(length = 150, nullable = false)
    private String correo;

    @ManyToOne
    @JoinColumn(name = "sede_id")
    private Sede sede;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public Trabajador(Integer id, String dni, String nombre, String telefono, String correo, Sede sede) {
        super();
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.sede = sede;
    }

    public Trabajador() {
        super();
    }

    public Trabajador(String dni, String nombre, String telefono, String correo, Sede sede) {
        super();
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.sede = sede;
    }

    public Trabajador(String dni) {
        super();
        this.dni = dni;
    }
}

package com.Project.Project.Modelo;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_usuario")
@ToString @EqualsAndHashCode
public class Usuario {

    @OneToMany(mappedBy = "usuario")
    private List<Producto> productos;

    @OneToMany(mappedBy = "usuario")
    private List<Orden> ordenes;
    public Usuario(){
    }
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "ID_usuario")
    private Integer id;

    @Getter @Setter @Column(name = "Nombre")
    private String nombre;

    @Getter @Setter @Column(name = "Email")
    private String email;

    @Getter @Setter @Column(name = "Direccion")
    private String direccion;

    @Getter @Setter @Column(name = "Pass")
    private String password;

    @Getter @Setter @Column(name = "Tipo")
    private String tipo;

    public Usuario(Integer id, String nombre, String email, String password, String direccion, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
        this.tipo = tipo;
    }

    public List<Producto> getProductos() {

        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

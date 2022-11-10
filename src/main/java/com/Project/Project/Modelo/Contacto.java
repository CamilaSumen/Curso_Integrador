package com.Project.Project.Modelo;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "tb_contacto")
@ToString
@EqualsAndHashCode
public class Contacto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "ID_contacto")
    private Long id;

    @Getter @Setter @Column(name = "Nombre")
    private String nombre;

    @Getter @Setter @Column(name = "Correo")
    private String correo;

    @Getter @Setter @Column(name = "Telefono")
    private String telefono;

    @Getter @Setter @Column(name = "Mensaje")
    private String mensaje;
}

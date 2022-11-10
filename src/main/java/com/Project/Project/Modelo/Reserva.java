package com.Project.Project.Modelo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "tb_reserva")
@ToString
@EqualsAndHashCode
public class Reserva {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "ID_reserva")
    private Long id;

    @Getter @Setter @Column(name = "Sede")
    private String sede;

    @Getter @Setter @Column(name = "Nombres")
    private String nombre;

    @Getter @Setter @Column(name = "Apellidos")
    private String apellido;

    @Getter @Setter @Column(name = "Correo")
    private String correo;

    @Getter @Setter @Column(name = "Telefono")
    private String telefono;

    @Getter @Setter @Column(name = "Fecha")
    private Date fecha;

    @Getter @Setter @Column(name = "Hora")
    private Time hora;

    @Getter @Setter @Column(name = "Cantidad")
    private Integer cantidad;

    @Getter @Setter @Column(name = "Comentario")
    private String comentario;

}

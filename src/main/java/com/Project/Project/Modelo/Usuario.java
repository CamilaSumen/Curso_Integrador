package com.Project.Project.Modelo;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "tb_usuario")
@ToString @EqualsAndHashCode
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "ID_usuario")
    private Long id;

    @Getter @Setter @Column(name = "Nombre")
    private String nombre;

    @Getter @Setter @Column(name = "Email")
    private String email;

    @Getter @Setter @Column(name = "Pass")
    private String password;
    
}

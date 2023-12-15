package com.usuario.app.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "t_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String email;
}

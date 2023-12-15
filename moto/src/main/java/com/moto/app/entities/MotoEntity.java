package com.moto.app.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "t_moto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String marca;
    private String modelo;
    private long usuarioId;
}

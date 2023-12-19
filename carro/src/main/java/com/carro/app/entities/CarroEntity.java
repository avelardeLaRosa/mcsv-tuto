package com.carro.app.entities;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "t_carro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long usuarioId;
    private String marca;
    private String modelo;

}

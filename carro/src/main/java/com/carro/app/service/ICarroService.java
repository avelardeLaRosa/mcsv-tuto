package com.carro.app.service;

import com.carro.app.entities.CarroEntity;

import java.util.List;

public interface ICarroService {


    CarroEntity add(CarroEntity u);

    CarroEntity update(CarroEntity u);

    CarroEntity get(long id);

    List<CarroEntity> getAll();

    void delete(long id);

    List<CarroEntity> getAllByUsuario(long usuarioId);



}

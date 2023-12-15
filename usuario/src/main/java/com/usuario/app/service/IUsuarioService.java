package com.usuario.app.service;

import com.usuario.app.entities.UsuarioEntity;
import com.usuario.app.models.CarroModel;
import com.usuario.app.models.MotoModel;

import java.util.List;

public interface IUsuarioService {
    UsuarioEntity add(UsuarioEntity u);

    UsuarioEntity update(UsuarioEntity u);

    UsuarioEntity get(long id);

    List<UsuarioEntity> getAll();

    void delete(long id);

    List<CarroModel> getCarros(long usuarioId);
    List<MotoModel> getMotos(long usuarioId);

    CarroModel saveCarro(long usuarioid,CarroModel carro);
    MotoModel saveMoto(long usuarioid,MotoModel moto);
}

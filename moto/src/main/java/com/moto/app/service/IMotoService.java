package com.moto.app.service;


import com.moto.app.entities.MotoEntity;

import java.util.List;

public interface IMotoService {

    MotoEntity add(MotoEntity u);

    MotoEntity update(MotoEntity u);

    MotoEntity get(long id);

    List<MotoEntity> getAll();

    void delete(long id);

    List<MotoEntity> getAllByUsuario(long usuarioId);

}

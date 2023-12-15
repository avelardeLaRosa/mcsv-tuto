package com.moto.app.service;

import com.moto.app.entities.MotoEntity;
import com.moto.app.repository.IMotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotoServiceImpl implements IMotoService {

    private final IMotoRepository repository;

    @Autowired
    public MotoServiceImpl(IMotoRepository repository) {
        this.repository = repository;
    }


    @Override
    public MotoEntity add(MotoEntity u) {
        return repository.save(u);
    }

    @Override
    public MotoEntity update(MotoEntity u) {
        Optional<MotoEntity> optionalMoto = repository.findById(u.getId());
        if (optionalMoto.isEmpty()) {
            return null;
        }
        return repository.save(u);
    }

    @Override
    public MotoEntity get(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<MotoEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public List<MotoEntity> getAllByUsuario(long usuarioId) {
        List<MotoEntity> carroEntities = repository.findAllByUsuarioId(usuarioId);
        if (carroEntities.isEmpty()) {
            return null;
        }
        return carroEntities;
    }
}

package com.carro.app.service.impl;

import com.carro.app.entities.CarroEntity;
import com.carro.app.repository.ICarroRepository;
import com.carro.app.service.ICarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroServiceimpl implements ICarroService {

    private final ICarroRepository repository;

    @Autowired
    public CarroServiceimpl(ICarroRepository repository) {
        this.repository = repository;
    }

    @Override
    public CarroEntity add(CarroEntity u) {
        return repository.save(u);
    }

    @Override
    public CarroEntity update(CarroEntity u) {
        Optional<CarroEntity> optionalCarro = repository.findById(u.getId());
        if (optionalCarro.isEmpty()) {
            return null;
        }
        return repository.save(u);
    }

    @Override
    public CarroEntity get(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<CarroEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public List<CarroEntity> getAllByUsuario(long usuarioId) {
        List<CarroEntity> carroEntities = repository.findAllByUsuarioId(usuarioId);
        if (carroEntities.isEmpty()) {
            return null;
        }
        return carroEntities;
    }
}

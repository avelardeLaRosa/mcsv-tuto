package com.carro.app.repository;

import com.carro.app.entities.CarroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICarroRepository extends JpaRepository<CarroEntity,Long> {

    List<CarroEntity> findAllByUsuarioId(long id);
}

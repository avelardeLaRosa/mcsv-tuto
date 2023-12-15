package com.moto.app.repository;

import com.moto.app.entities.MotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMotoRepository extends JpaRepository<MotoEntity,Long> {

    List<MotoEntity> findAllByUsuarioId(long id);
}

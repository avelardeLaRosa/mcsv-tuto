package com.moto.app.controller;

import com.moto.app.entities.MotoEntity;
import com.moto.app.service.IMotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moto")
public class MotoRESTController {
    private final IMotoService service;

    @Autowired
    public MotoRESTController(IMotoService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<MotoEntity>> getAll() {
        if (service.getAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<MotoEntity>> getAllByUser(@PathVariable long id) {
        if (service.getAllByUsuario(id).isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(service.getAllByUsuario(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoEntity> get(@PathVariable long id) {
        if (service.get(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping
    public ResponseEntity<MotoEntity> add(@RequestBody MotoEntity u) {
        return ResponseEntity.ok(service.add(u));
    }

    @PutMapping
    public ResponseEntity<MotoEntity> update(@RequestBody MotoEntity u) {
        if (service.update(u) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.add(u));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.ok("Registro Eliminado");
    }


}

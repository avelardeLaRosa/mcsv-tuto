package com.carro.app.controller;

import com.carro.app.entities.CarroEntity;
import com.carro.app.service.ICarroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroRESTController {
    private final ICarroService service;

    public CarroRESTController(ICarroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CarroEntity>> getAll() {
        if (service.getAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<CarroEntity>> getAllByUser(@PathVariable long id) {
        if (service.getAllByUsuario(id).isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(service.getAllByUsuario(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroEntity> get(@PathVariable long id) {
        if (service.get(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping
    public ResponseEntity<CarroEntity> add(@RequestBody CarroEntity u) {
        return ResponseEntity.ok(service.add(u));
    }

    @PutMapping
    public ResponseEntity<CarroEntity> update(@RequestBody CarroEntity u) {
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

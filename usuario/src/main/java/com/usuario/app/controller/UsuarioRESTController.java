package com.usuario.app.controller;

import com.usuario.app.entities.UsuarioEntity;
import com.usuario.app.models.CarroModel;
import com.usuario.app.models.MotoModel;
import com.usuario.app.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioRESTController {
    private final IUsuarioService service;

    @Autowired
    public UsuarioRESTController(IUsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> getAll() {
        if (service.getAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/carro/{id}")
    public ResponseEntity<List<CarroModel>> getAllCarros(@PathVariable long id) {
        if (service.get(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.getCarros(id));
    }

    @GetMapping("/moto/{id}")
    public ResponseEntity<List<MotoModel>> getAllMotos(@PathVariable long id) {
        if (service.get(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.getMotos(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> get(@PathVariable long id) {
        if (service.get(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioEntity> add(@RequestBody UsuarioEntity u) {
        return ResponseEntity.ok(service.add(u));
    }

    @PostMapping("/carro/{id}")
    public ResponseEntity<CarroModel> addCarro(@PathVariable long id, @RequestBody CarroModel u) {
        return ResponseEntity.ok(service.saveCarro(id, u));
    }

    @PostMapping("/moto/{id}")
    public ResponseEntity<MotoModel> addMoto(@PathVariable long id, @RequestBody MotoModel u) {
        return ResponseEntity.ok(service.saveMoto(id, u));
    }

    @PutMapping
    public ResponseEntity<UsuarioEntity> update(@RequestBody UsuarioEntity u) {
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

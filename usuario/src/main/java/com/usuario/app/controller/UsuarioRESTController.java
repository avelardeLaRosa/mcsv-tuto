package com.usuario.app.controller;

import com.usuario.app.entities.UsuarioEntity;
import com.usuario.app.models.CarroModel;
import com.usuario.app.models.MotoModel;
import com.usuario.app.service.IUsuarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @CircuitBreaker(name = "carrosCB", fallbackMethod = "fallBackGetCarros")
    @GetMapping("/carro/{id}")
    public ResponseEntity<List<CarroModel>> getAllCarros(@PathVariable long id) {
        if (service.get(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.getCarros(id));
    }

    @CircuitBreaker(name = "motosCB", fallbackMethod = "fallBackGetMotos")
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

    @CircuitBreaker(name = "carrosCB", fallbackMethod = "fallBackSaveCarro")
    @PostMapping("/carro/{id}")
    public ResponseEntity<CarroModel> addCarro(@PathVariable long id, @RequestBody CarroModel u) {
        return ResponseEntity.ok(service.saveCarro(id, u));
    }

    @CircuitBreaker(name = "motosCB", fallbackMethod = "fallBackSaveMoto")
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


    private ResponseEntity<List<CarroModel>> fallBackGetCarros(
            @PathVariable("id") long id,
            RuntimeException exception
    ) {
        return new ResponseEntity("El usuario : " + id + " tiene los carros en el taller", HttpStatus.OK);
    }

    private ResponseEntity<CarroModel> fallBackSaveCarro(
            @PathVariable("id") long id,
            @RequestBody CarroModel carroModel,
            RuntimeException exception
    ) {
        return new ResponseEntity("El usuario : " + id + " no tiene dinero para los carros", HttpStatus.OK);
    }


    private ResponseEntity<List<CarroModel>> fallBackGetMotos(
            @PathVariable("id") long id,
            RuntimeException exception
    ) {
        return new ResponseEntity("El usuario : " + id + " tiene las motos en el taller", HttpStatus.OK);
    }

    private ResponseEntity<CarroModel> fallBackSaveMoto(
            @PathVariable("id") long id,
            @RequestBody CarroModel carroModel,
            RuntimeException exception
    ) {
        return new ResponseEntity("El usuario : " + id + " no tiene dinero para las motos", HttpStatus.OK);
    }
}



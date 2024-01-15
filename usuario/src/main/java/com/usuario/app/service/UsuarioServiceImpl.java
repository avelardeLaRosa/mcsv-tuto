package com.usuario.app.service;

import com.usuario.app.entities.UsuarioEntity;
import com.usuario.app.feignclients.CarroFeignClient;
import com.usuario.app.feignclients.MotoFeignClient;
import com.usuario.app.models.CarroModel;
import com.usuario.app.models.MotoModel;
import com.usuario.app.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuarioRepository repository;
    private final RestTemplate restTemplate;
    private final CarroFeignClient carroFeignClient;
    private final MotoFeignClient motoFeignClient;

    @Autowired
    public UsuarioServiceImpl(IUsuarioRepository repository, RestTemplate restTemplate, CarroFeignClient carroFeignClient, MotoFeignClient motoFeignClient) {
        this.repository = repository;
        this.restTemplate = restTemplate;
        this.carroFeignClient = carroFeignClient;
        this.motoFeignClient = motoFeignClient;
    }

    @Override
    public UsuarioEntity add(UsuarioEntity u) {
        return repository.save(u);
    }

    @Override
    public UsuarioEntity update(UsuarioEntity u) {
        Optional<UsuarioEntity> optionalUsuarioEntity = repository.findById(u.getId());
        if (optionalUsuarioEntity.isEmpty()) {
            return null;
        }
        return repository.save(u);
    }

    @Override
    public UsuarioEntity get(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<UsuarioEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }


    @Override
    public List<CarroModel> getCarros(long usuarioId) {
        List<CarroModel> carroModels = restTemplate.getForObject("http://carro-service/carro/usuario/" + usuarioId, List.class);
        return carroModels;
    }

    @Override
    public List<MotoModel> getMotos(long usuarioId) {
        List<MotoModel> motoModels = restTemplate.getForObject("http://moto-service/moto/usuario/" + usuarioId, List.class);
        return motoModels;
    }

    @Override
    public CarroModel saveCarro(long usuarioId, CarroModel carro) {
        carro.setUsuarioId(usuarioId);
        return carroFeignClient.save(carro);
    }

    @Override
    public MotoModel saveMoto(long usuarioid, MotoModel moto) {
        moto.setUsuarioId(usuarioid);
        return motoFeignClient.save(moto);
    }
}

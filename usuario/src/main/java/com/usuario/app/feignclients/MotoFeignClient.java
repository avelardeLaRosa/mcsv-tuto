package com.usuario.app.feignclients;

import com.usuario.app.models.CarroModel;
import com.usuario.app.models.MotoModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "moto-service")
@RequestMapping("/moto")
public interface MotoFeignClient {

    @PostMapping
    MotoModel save(@RequestBody MotoModel carro);
}

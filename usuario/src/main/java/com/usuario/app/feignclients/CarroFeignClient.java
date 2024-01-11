package com.usuario.app.feignclients;

import com.usuario.app.models.CarroModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "carro-service")
@RequestMapping("/carro")
public interface CarroFeignClient {

    @PostMapping
    CarroModel save(@RequestBody CarroModel carro);

}

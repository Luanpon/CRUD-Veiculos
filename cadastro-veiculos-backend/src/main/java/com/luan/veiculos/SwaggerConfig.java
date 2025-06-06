package com.luan.veiculos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Rest de Gestão de Veículos")
                        .version("1.0")
                        .description("Documentação da API. Desenvolvido por Luan Pontes."));
    }
}

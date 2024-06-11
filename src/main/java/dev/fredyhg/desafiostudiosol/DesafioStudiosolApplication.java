package dev.fredyhg.desafiostudiosol;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Desafio StudioSol", version = "1.0", description = "Routes documentations"))
@SpringBootApplication
public class DesafioStudiosolApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioStudiosolApplication.class, args);
    }

}

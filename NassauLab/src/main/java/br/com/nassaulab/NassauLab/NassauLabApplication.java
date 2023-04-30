package br.com.nassaulab.NassauLab;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "NassauLabApi",version = "1.0.0", description = "project of Spring Boot for app Back and"),
		servers = {
				@Server(url = "http://localhost:5000")
		}

)
public class NassauLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(NassauLabApplication.class, args);
	}

}

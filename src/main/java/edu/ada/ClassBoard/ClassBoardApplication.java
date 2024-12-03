package edu.ada.ClassBoard;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(servers = { @Server(url = "/",
		description = "default server url")}) // problema de CORS
@SpringBootApplication
public class ClassBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassBoardApplication.class, args);
	}

}

package org.example.grupo20integrador3;

import jakarta.annotation.PostConstruct;
import org.example.grupo20integrador3.utils.CargaDeDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Grupo20Integrador3Application {

	@Autowired
	private CargaDeDatos cargaDeDatos;

	public static void main(String[] args) {
		SpringApplication.run(Grupo20Integrador3Application.class, args);
	}

@PostConstruct
public void init() throws Exception {
	cargaDeDatos.cargarDatosDesdeCsv();
}
}

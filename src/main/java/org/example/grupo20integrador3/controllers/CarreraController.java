package org.example.grupo20integrador3.controllers;

import org.example.grupo20integrador3.dtos.CarreraDTO;
import org.example.grupo20integrador3.repositories.CarreraRepository;
import org.example.grupo20integrador3.services.CarreraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraServicio carreraServicio;

    public CarreraController(CarreraServicio carreraServicio) {
        this.carreraServicio = carreraServicio;
    }

    @GetMapping("/conInscriptosOrdenadas")
    public Iterable<CarreraDTO> getCarrerasConInscriptosOrdenadas() throws Exception {
        return carreraServicio.getCarrerasConInscriptosOrdenadas();
    }
}

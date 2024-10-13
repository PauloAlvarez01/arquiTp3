package org.example.grupo20integrador3.controllers;

import org.example.grupo20integrador3.dtos.CarreraDTO;
import org.example.grupo20integrador3.dtos.ReporteCarreraDTO;
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

    /*f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
    */
    @GetMapping("/conInscriptosOrdenadas")
    public Iterable<CarreraDTO> getCarrerasConInscriptosOrdenadas() throws Exception {
        return carreraServicio.getCarrerasConInscriptosOrdenadas();
    }

    /*h) generar un reporte de las carreras, que para cada carrera incluya información de los
    inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y
    presentar los años de manera cronológica
    */
    @GetMapping("/reporte")
    public Iterable<ReporteCarreraDTO> getCarrerasReporte() throws Exception {
        return carreraServicio.getCarrerasReporte();
    }
}

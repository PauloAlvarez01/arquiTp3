package org.example.grupo20integrador3.repositories;

import org.example.grupo20integrador3.dtos.CarreraDTO;
import org.example.grupo20integrador3.entities.Carrera;
import org.example.grupo20integrador3.entities.Estudiante;
import org.example.grupo20integrador3.entities.EstudianteCarrera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstudianteCarreraRepository extends JpaRepository<EstudianteCarrera, Long> {

    List<EstudianteCarrera> findByEstudianteAndCarrera(Estudiante estudiante, Carrera carrera);

}


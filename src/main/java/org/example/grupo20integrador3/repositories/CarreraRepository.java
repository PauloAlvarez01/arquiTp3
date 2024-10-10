package org.example.grupo20integrador3.repositories;

import org.example.grupo20integrador3.entities.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

    Optional<Carrera> findByIdCarrera(int idCarrera);


}

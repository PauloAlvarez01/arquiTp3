package org.example.grupo20integrador3.repositories;

import org.example.grupo20integrador3.dtos.CarreraDTO;
import org.example.grupo20integrador3.entities.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

    Optional<Carrera> findByIdCarrera(int idCarrera);

    Carrera findCarreraByIdCarrera(int idCarrera);

    Optional<Carrera> findByNombre(String carreraString);

    @Query ("SELECT c.nombre FROM Carrera c JOIN c.inscriptos ec GROUP BY c.nombre HAVING COUNT(ec) > 0 ORDER BY COUNT(ec) DESC")
    public List<String> getCarrerasConInscriptosOrdenadas();

}

package org.example.grupo20integrador3.repositories;

import org.example.grupo20integrador3.dtos.CarreraDTO;
import org.example.grupo20integrador3.dtos.ReporteCarreraDTO;
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

    @Query(value = "SELECT inscripciones_egresos.nombre_carrera AS nombre_carrera, inscripciones_egresos.anio AS anio, " +
            "SUM(inscripciones_egresos.estudiantes_inscriptos) AS estudiantes_inscriptos, " +
            "SUM(inscripciones_egresos.estudiantes_egresados) AS estudiantes_egresados " +
            "FROM ( " +
            "    SELECT ec.inscripcion AS anio, ca.nombre AS nombre_carrera, COUNT(ec.id_estudiante) AS estudiantes_inscriptos, 0 AS estudiantes_egresados " +
            "    FROM carrera ca " +
            "    JOIN estudiante_carrera ec ON ca.id_carrera = ec.id_carrera " +
            "    JOIN estudiante e ON e.LU = ec.id_estudiante " +
            "    GROUP BY ca.nombre, ec.inscripcion " +
            "    UNION ALL " +
            "    SELECT ec.graduacion AS anio, ca.nombre AS nombre_carrera, 0 AS estudiantes_inscriptos, COUNT(ec.id_estudiante) AS estudiantes_egresados " +
            "    FROM carrera ca " +
            "    JOIN estudiante_carrera ec ON ca.id_carrera = ec.id_carrera " +
            "    JOIN estudiante e ON e.LU = ec.id_estudiante " +
            "    WHERE ec.graduacion <> 0 " +
            "    GROUP BY ca.nombre, ec.graduacion " +
            ") AS inscripciones_egresos " +
            "GROUP BY inscripciones_egresos.nombre_carrera, inscripciones_egresos.anio " +
            "ORDER BY inscripciones_egresos.nombre_carrera, inscripciones_egresos.anio",
            nativeQuery = true)
    List<Object[]> getCarrerasReporte();
}

package org.example.grupo20integrador3.repositories;

import org.example.grupo20integrador3.dtos.EstudianteDTO;
import org.example.grupo20integrador3.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

/*c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
 */
    @Query ("SELECT e FROM Estudiante e ORDER BY e.apellido ASC")
    public List<Estudiante> getEstudiantesOrdenApellido();


    Optional<Estudiante> findByDNI(int dni);

    Optional<Estudiante> findByLU(int LU);

    List<Estudiante> findEstudiantesByGenero(String genero);
    @Query("SELECT e  " +
            "FROM EstudianteCarrera ec JOIN ec.carrera c JOIN ec.estudiante e " +
            "WHERE c.nombre = :nombreCarrera " +
            "AND e.ciudad = :nombreLocalidad ")
    List<Estudiante> findEstudiantesByCarreraAndLocalidad(@Param("nombreCarrera") String carrera,@Param("nombreLocalidad")  String localidad);
}

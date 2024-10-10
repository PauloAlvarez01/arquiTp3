package org.example.grupo20integrador3.repositories;

import org.example.grupo20integrador3.dtos.EstudianteDTO;
import org.example.grupo20integrador3.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

/*c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
 */
    @Query ("SELECT e FROM Estudiante e ORDER BY e.apellido ASC")
    public List<Estudiante> getEstudiantesOrdenApellido();


    Optional<Estudiante> findByDNI(int dni);

    Optional<Estudiante> findByLU(int LU);
}

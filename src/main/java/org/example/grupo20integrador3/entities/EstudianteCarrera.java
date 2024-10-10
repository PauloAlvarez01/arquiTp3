package org.example.grupo20integrador3.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.example.grupo20integrador3.repositories.EstudianteRepository;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
public class EstudianteCarrera implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private int id_estudiante;
    @Column
    private int id_carrera;
    @Column
    private int inscripcion;
    @Column
    private int graduacion;
    @Column
    private int antiguedad;

    @ManyToOne
    private Estudiante estudiante;

    @ManyToOne
    private Carrera carrera;

    public EstudianteCarrera() {

    }

    public EstudianteCarrera(int id_estudiante, int id_carrera, int inscripcion, int graduacion, int antiguedad) {
        this.id = id;
        this.id_estudiante = id_estudiante;
        this.id_carrera = id_carrera;
        this.inscripcion = inscripcion;
        this.graduacion = graduacion;
        this.antiguedad = antiguedad;
        this.estudiante = null; //repositoryEst.findBy(id_estudiante); IMPLEMENTAR
        this.carrera = null;
    }

    public EstudianteCarrera( int inscripcion, int graduacion, int antiguedad, Estudiante estudiante, Carrera carrera) {
        this.id_estudiante = estudiante.getLU();
        this.id_carrera = carrera.getIdCarrera();
        this.inscripcion = inscripcion;
        this.graduacion = graduacion;
        this.antiguedad = antiguedad;
        this.estudiante = estudiante;
        this.carrera = carrera;
    }


}

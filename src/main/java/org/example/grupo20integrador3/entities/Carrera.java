package org.example.grupo20integrador3.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Carrera implements Serializable {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int idCarrera;
    @Column
    private String nombre;
    @Column
    private int duracion;

    @OneToMany(mappedBy = "carrera")
    private List<EstudianteCarrera> inscriptos;

    public Carrera() {

    }

    public Carrera(int idCarrera, String nombre, int duracion) {
        this.idCarrera = idCarrera;
        this.nombre = nombre;
        this.duracion = duracion;
    }

    public Carrera(String nombre, int duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.inscriptos = new ArrayList<EstudianteCarrera>();
    }

}

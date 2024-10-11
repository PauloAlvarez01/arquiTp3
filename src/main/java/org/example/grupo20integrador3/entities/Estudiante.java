package org.example.grupo20integrador3.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
public class Estudiante implements Serializable {
    @Column
    private int DNI;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private int edad;
    @Column
    private String genero;
    @Column
    private String ciudad;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int LU;

    @OneToMany(mappedBy = "estudiante")  //se refiere al atributo de estudianteCarrera
    private List<EstudianteCarrera> carrerasInscriptas;

    public Estudiante() {

    }

    public Estudiante(int DNI, String nombre, String apellido, int edad, String genero, String ciudad) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad = ciudad;
        this.carrerasInscriptas = new ArrayList<EstudianteCarrera>();
    }

    public ArrayList<EstudianteCarrera> getCarrerasInscriptas() {
        return new ArrayList<EstudianteCarrera>(carrerasInscriptas);
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "DNI=" + DNI +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", LU=" + LU +
                ", carrerasInscriptas=" + carrerasInscriptas +
                '}';
    }
}

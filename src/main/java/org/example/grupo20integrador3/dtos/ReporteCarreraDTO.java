package org.example.grupo20integrador3.dtos;

import java.io.Serializable;

public class ReporteCarreraDTO implements Serializable {
    private String nombreCarrera;
    private int anio;
    private int estudiantesInscriptos;
    private int estudiantesEgresados;

    public ReporteCarreraDTO(String nombreCarrera, int anio, int estudiantesInscriptos, int estudiantesEgresados) {
        this.nombreCarrera = nombreCarrera;
        this.anio = anio;
        this.estudiantesInscriptos = estudiantesInscriptos;
        this.estudiantesEgresados = estudiantesEgresados;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getEstudiantesInscriptos() {
        return estudiantesInscriptos;
    }

    public void setEstudiantesInscriptos(int estudiantesInscriptos) {
        this.estudiantesInscriptos = estudiantesInscriptos;
    }

    public int getEstudiantesEgresados() {
        return estudiantesEgresados;
    }

    public void setEstudiantesEgresados(int estudiantesEgresados) {
        this.estudiantesEgresados = estudiantesEgresados;
    }

}

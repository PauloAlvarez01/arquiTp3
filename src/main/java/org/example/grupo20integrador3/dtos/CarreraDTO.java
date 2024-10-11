package org.example.grupo20integrador3.dtos;

public class CarreraDTO {

    private String nombreCarrera;
    private Long inscriptos;

    public CarreraDTO(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
        this.inscriptos = 0L;
    }

    public CarreraDTO(String nombreCarrera, Long inscriptos) {
        this.nombreCarrera = nombreCarrera;
        this.inscriptos = inscriptos;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public Long getInscriptos() {
        return inscriptos;
    }

    public void setInscriptos(Long inscriptos) {
        this.inscriptos = inscriptos;
    }

    @Override
    public String toString() {
        return "CarreraDTO{" +
                "nombreCarrera='" + nombreCarrera + '\'';
    }
}

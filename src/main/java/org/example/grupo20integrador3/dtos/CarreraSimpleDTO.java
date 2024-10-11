package org.example.grupo20integrador3.dtos;

public class CarreraSimpleDTO {

    private String nombreCarrera;

    public CarreraSimpleDTO(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }


    @Override
    public String toString() {
        return "CarreraDTO{" +
                "nombreCarrera='" + nombreCarrera +"'";
    }
}

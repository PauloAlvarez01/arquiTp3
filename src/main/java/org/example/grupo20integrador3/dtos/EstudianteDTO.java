package org.example.grupo20integrador3.dtos;

import java.util.List;

public class EstudianteDTO {
    private String nombres;
    private String apellido;
    private int edad;
    private String genero;
    private int dni;
    private String localidad;
    private int nro_libreta;
    private List<EstudianteCarreraDTO> carreras;

    public EstudianteDTO(String nombres, String apellido, String genero, String localidad) {
        this.nombres = nombres;
        this.apellido = apellido;
        this.genero = genero;
        this.localidad = localidad;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public int getNro_libreta() {
        return nro_libreta;
    }

    public void setNro_libreta(int nro_libreta) {
        this.nro_libreta = nro_libreta;
    }

    public List<EstudianteCarreraDTO> getCarreras() {
        return carreras;
    }

    public void addCarrera(){

    }

    @Override
    public String toString() {
        return " EstudianteDTO{" +
                "nombres='" + nombres + '\'' +
                ", apellido='" + apellido + '\'' +
                ", genero='" + genero + '\'' +
                ", localidad='" + localidad + '\'' +
                '}';
    }
}

package org.example.grupo20integrador3.dtos;

import java.util.List;

public class EstudianteDTO {
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private int dni;
    private String ciudad;
    private int nro_libreta;
    private List<EstudianteCarreraDTO> carreras;


    public EstudianteDTO(String nombre, String apellido, int edad, String genero, int dni, String ciudad, int nro_libreta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.dni = dni;
        this.ciudad = ciudad;
        this.nro_libreta = nro_libreta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
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

    @Override
    public String toString() {
        return "EstudianteDTO{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", dni=" + dni +
                ", ciudad='" + ciudad + '\'' +
                ", nro_libreta=" + nro_libreta +
                '}';
    }
}

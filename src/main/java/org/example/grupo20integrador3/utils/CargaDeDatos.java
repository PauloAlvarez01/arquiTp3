package org.example.grupo20integrador3.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.grupo20integrador3.dtos.EstudianteCarreraRequestDTO;
import org.example.grupo20integrador3.entities.Carrera;
import org.example.grupo20integrador3.entities.Estudiante;
import org.example.grupo20integrador3.entities.EstudianteCarrera;
import org.example.grupo20integrador3.repositories.CarreraRepository;
import org.example.grupo20integrador3.repositories.EstudianteCarreraRepository;
import org.example.grupo20integrador3.repositories.EstudianteRepository;
import org.example.grupo20integrador3.services.EstudianteCarreraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Optional;

@Component
public class CargaDeDatos {

    private EstudianteRepository estudianteRepository;
    private CarreraRepository carreraRepository;
    private EstudianteCarreraServicio estudianteCarreraServicio;

    @Autowired
    public CargaDeDatos(EstudianteRepository estudianteRepository, CarreraRepository carreraRepository, EstudianteCarreraServicio estudianteCarreraServicio) {
        this.estudianteRepository = estudianteRepository;
        this.carreraRepository = carreraRepository;
        this.estudianteCarreraServicio = estudianteCarreraServicio;
    }



    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "src\\main\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};  // Puedes configurar tu encabezado personalizado aquí si es necesario
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void cargarDatosDesdeCsv() throws Exception {
        // Cargar estudiantes
        for (CSVRecord row : getData("estudiantes.csv")) {
            if (row.size() >= 7) {
                String DNIString = row.get(0);
                String nombre = row.get(1);
                String apellido = row.get(2);
                String edadString = row.get(3);
                String genero = row.get(4);
                String ciudad = row.get(5);

                if (!DNIString.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !edadString.isEmpty() && !genero.isEmpty() && !ciudad.isEmpty()) {
                    try {
                        int DNI = Integer.parseInt(DNIString);
                        // Verificar si el estudiante ya existe
                        Optional<Estudiante> estudianteExistente = estudianteRepository.findByDNI(DNI);
                        if (!estudianteExistente.isPresent()) {
                            int edad = Integer.parseInt(edadString);
                            Estudiante estudiante = new Estudiante(DNI, nombre, apellido, edad, genero, ciudad);
                            estudianteRepository.save(estudiante);
                        } else {
                            System.out.println("Estudiante con DNI " + DNI + " ya existe.");
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de estudiante: " + e.getMessage());
                    }
                }
            }
        }

        // Cargar carreras
        for (CSVRecord row : getData("carreras.csv")) {
            if (row.size() >= 3) {
                String carreraString = row.get(1);
                String tiempoDuracion = row.get(2);

                if (!carreraString.isEmpty() && !tiempoDuracion.isEmpty()) {
                    try {
                        // Verificar si la carrera ya existe
                        Optional<Carrera> carreraExistente = carreraRepository.findByNombre(carreraString);
                        if (!carreraExistente.isPresent()) {
                            int duracion = Integer.parseInt(tiempoDuracion);
                            Carrera carrera = new Carrera(carreraString, duracion);
                            carreraRepository.save(carrera);
                        } else {
                            System.out.println("La carrera  " + carreraString + " ya existe.");
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de carrera: " + e.getMessage());
                    }
                }
            }
        }

        // Cargar relaciones estudiante-carrera
        for (CSVRecord row : getData("estudianteCarrera.csv")) {
            if (row.size() >= 6) {
                String un_dni_estudiante = row.get(1);
                String un_id_carrera = row.get(2);
                String una_inscripcion = row.get(3);
                String una_graduacion = row.get(4);
                String una_antiguedad = row.get(5);

                if (!un_dni_estudiante.isEmpty() && !un_id_carrera.isEmpty() && !una_inscripcion.isEmpty() && !una_graduacion.isEmpty() && !una_antiguedad.isEmpty()) {
                    try {
                        int dni_estudiante = Integer.parseInt(un_dni_estudiante);
                        int idCarrera = Integer.parseInt(un_id_carrera);
                        int inscripcion = Integer.parseInt(una_inscripcion);
                        int graduacion = Integer.parseInt(una_graduacion);
                        int antiguedad = Integer.parseInt(una_antiguedad);

                        EstudianteCarreraRequestDTO matriculacion = new EstudianteCarreraRequestDTO(inscripcion, graduacion,antiguedad, dni_estudiante, idCarrera);
                        estudianteCarreraServicio.save (matriculacion);


                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de estudianteCarrera: " + e.getMessage());
                    }
                }
            }
        }




    }
}
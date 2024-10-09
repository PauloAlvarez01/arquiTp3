package org.example.grupo20integrador3.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.grupo20integrador3.entities.Carrera;
import org.example.grupo20integrador3.entities.Estudiante;
import org.example.grupo20integrador3.entities.EstudianteCarrera;
import org.example.grupo20integrador3.repositories.CarreraRepository;
import org.example.grupo20integrador3.repositories.EstudianteCarreraRepository;
import org.example.grupo20integrador3.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Component
public class CargaDeDatos {

    private EstudianteRepository estudianteRepository;
    private CarreraRepository carreraRepository;
    private EstudianteCarreraRepository estudianteCarreraRepository;

    @Autowired
    public CargaDeDatos(EstudianteRepository estudianteRepository, CarreraRepository carreraRepository, EstudianteCarreraRepository estudianteCarreraRepository) {
        this.estudianteRepository = estudianteRepository;
        this.carreraRepository = carreraRepository;
        this.estudianteCarreraRepository = estudianteCarreraRepository;
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


        for (CSVRecord row : getData("estudiantes.csv")) {
            if (row.size() >= 7) { // Verificar que hay al menos 7 campos en el CSVRecord
                String DNIString = row.get(0);
                String nombre = row.get(1);
                String apellido = row.get(2);
                String edadString = row.get(3);
                String genero = row.get(4);
                String ciudad = row.get(5);
                String LUString = row.get(6);

                if (!DNIString.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !edadString.isEmpty() && !genero.isEmpty() && !ciudad.isEmpty() && !LUString.isEmpty()) {
                    try {
                        int DNI = Integer.parseInt(DNIString);
                        int edad = Integer.parseInt(edadString);
                        int LU = Integer.parseInt(LUString);
                        Estudiante estudiante = new Estudiante(DNI, nombre, apellido, edad, genero, ciudad, LU);
                        estudianteRepository.save(estudiante);

                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de estudiante: " + e.getMessage());
                    }
                }
            }

        }

        for (CSVRecord row : getData("estudianteCarrera.csv")) {
            if (row.size() >= 6) { // Verificar que hay al menos 6 campos en el CSVRecord
                String un_id = row.get(0);
                String un_id_estudiante = row.get(1);
                String un_id_carrera = row.get(2);
                String una_inscripcion = row.get(3);
                String una_graduacion = row.get(4);
                String una_antiguedad = row.get(5);
                if (!un_id.isEmpty() && !un_id_estudiante.isEmpty() && !un_id_carrera.isEmpty() & !una_inscripcion.isEmpty() && !una_graduacion.isEmpty() && !una_antiguedad.isEmpty()) {
                    try {
                        int id = Integer.parseInt(un_id);
                        int id_estudiante = Integer.parseInt(un_id_estudiante);
                        int id_carrera = Integer.parseInt(un_id_carrera);
                        int inscripcion = Integer.parseInt(una_inscripcion);
                        int graduacion = Integer.parseInt(una_graduacion);
                        int antiguedad = Integer.parseInt(una_antiguedad);

                        EstudianteCarrera estudianteCarrera = new EstudianteCarrera(id, id_estudiante, id_carrera, inscripcion, graduacion, antiguedad);
                        estudianteCarreraRepository.save(estudianteCarrera);
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de estudianteCarrera: " + e.getMessage());
                    }
                }
            }
        }

        for (CSVRecord row : getData("carreras.csv")) {
            if (row.size() >= 3) { // Verificar que hay al menos 4 campos en el CSVRecord
                String id = row.get(0);
                String carreraString = row.get(1);
                String tiempoDuracion = row.get(2);

                if (!id.isEmpty() && !carreraString.isEmpty() && !tiempoDuracion.isEmpty()) {
                    try {
                        int id_carrera = Integer.parseInt(id);
                        int duracion = Integer.parseInt(tiempoDuracion);
                        Carrera carrera = new Carrera(id_carrera, carreraString, duracion);
                        carreraRepository.save(carrera);
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de carrera: " + e.getMessage());
                    }
                }
            }
        }
    }
}
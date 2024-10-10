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
import java.util.List;
import java.util.Optional;

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
        // Cargar estudiantes
        for (CSVRecord row : getData("estudiantes.csv")) {
            if (row.size() >= 7) {
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
                        // Verificar si el estudiante ya existe
                        Optional<Estudiante> estudianteExistente = estudianteRepository.findByDNI(DNI);
                        if (!estudianteExistente.isPresent()) {
                            int edad = Integer.parseInt(edadString);
                            int LU = Integer.parseInt(LUString);
                            Estudiante estudiante = new Estudiante(DNI, nombre, apellido, edad, genero, ciudad, LU);
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
                String id = row.get(0);
                String carreraString = row.get(1);
                String tiempoDuracion = row.get(2);

                if (!id.isEmpty() && !carreraString.isEmpty() && !tiempoDuracion.isEmpty()) {
                    try {
                        int idCarrera = Integer.parseInt(id);
                        // Verificar si la carrera ya existe
                        Optional<Carrera> carreraExistente = carreraRepository.findByIdCarrera(idCarrera);
                        if (!carreraExistente.isPresent()) {
                            int duracion = Integer.parseInt(tiempoDuracion);
                            Carrera carrera = new Carrera(idCarrera, carreraString, duracion);
                            carreraRepository.save(carrera);
                        } else {
                            System.out.println("La carrera con ID " + idCarrera + " ya existe.");
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
                String un_id_estudiante = row.get(1);
                String un_id_carrera = row.get(2);
                String una_inscripcion = row.get(3);
                String una_graduacion = row.get(4);
                String una_antiguedad = row.get(5);

                if (!un_id_estudiante.isEmpty() && !un_id_carrera.isEmpty() && !una_inscripcion.isEmpty() && !una_graduacion.isEmpty() && !una_antiguedad.isEmpty()) {
                    try {
                        int id_estudiante = Integer.parseInt(un_id_estudiante);
                        int idCarrera = Integer.parseInt(un_id_carrera);
                        int inscripcion = Integer.parseInt(una_inscripcion);
                        int graduacion = Integer.parseInt(una_graduacion);
                        int antiguedad = Integer.parseInt(una_antiguedad);

                        // Verificar que existen el estudiante y la carrera
                        Optional<Estudiante> estudianteOpt = estudianteRepository.findByDNI(id_estudiante);
                        Optional<Carrera> carreraOpt = carreraRepository.findByIdCarrera(idCarrera);

                        if (estudianteOpt.isPresent() && carreraOpt.isPresent()) {
                            EstudianteCarrera estudianteCarrera = new EstudianteCarrera(inscripcion, graduacion, antiguedad, estudianteOpt.get(), carreraOpt.get());

                            // Verificar si ya existe la relación
                            List<EstudianteCarrera> existingEstudianteCarrera = estudianteCarreraRepository.findByEstudianteAndCarrera(estudianteOpt.get(), carreraOpt.get());
                            if (existingEstudianteCarrera.isEmpty()) {
                                estudianteCarreraRepository.save(estudianteCarrera);
                            } else {
                                System.out.println("La relación ya existe para el estudiante " + id_estudiante + " y la carrera " + idCarrera);
                            }
                        } else {
                            System.out.println("No se encontró estudiante o carrera con los IDs proporcionados.");
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de estudianteCarrera: " + e.getMessage());
                    }
                }
            }
        }




    }
}
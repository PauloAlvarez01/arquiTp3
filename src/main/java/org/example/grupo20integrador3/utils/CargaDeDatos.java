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

        // Cargar relaciones estudiante-carrera
        for (CSVRecord row : getData("estudianteCarrera.csv")) {
            if (row.size() >= 6) {
                String un_id_estudiante = row.get(1);
                String un_id_carrera = row.get(2);

                // Supongamos que ya has recuperado el estudiante y la carrera usando sus IDs
                Optional<Estudiante> estudianteOpt = estudianteRepository.findByDNI(Integer.parseInt(un_id_estudiante)); // o el método que uses para buscar
                Optional<Carrera> carreraOpt = carreraRepository.findById(Integer.parseInt(un_id_carrera));

                if (estudianteOpt.isPresent() && carreraOpt.isPresent()) {
                    Estudiante estudiante = estudianteOpt.get();
                    Carrera carrera = carreraOpt.get();
                    // Verificar si la relación ya existe
                    Optional<EstudianteCarrera> existingEstudianteCarrera = estudianteCarreraRepository.findByEstudianteAndCarrera(estudiante, carrera);
                    if (!existingEstudianteCarrera.isPresent()) {
                        // Aquí creas tu objeto EstudianteCarrera y lo guardas
                    } else {
                        System.out.println("La relación estudiante-carrera ya está cargada.");
                    }
                } else {
                    System.out.println("Estudiante o carrera no encontrados.");
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
                        int id_carrera = Integer.parseInt(id);
                        // Verificar si la carrera ya existe
                        Optional<Carrera> carreraExistente = carreraRepository.findById(id_carrera);
                        if (!carreraExistente.isPresent()) {
                            int duracion = Integer.parseInt(tiempoDuracion);
                            Carrera carrera = new Carrera(id_carrera, carreraString, duracion);
                            carreraRepository.save(carrera);
                        } else {
                            System.out.println("La carrera con ID " + id_carrera + " ya existe.");
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de carrera: " + e.getMessage());
                    }
                }
            }
        }
    }
}
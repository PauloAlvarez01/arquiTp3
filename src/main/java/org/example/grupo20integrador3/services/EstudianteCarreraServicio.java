package org.example.grupo20integrador3.services;

import jakarta.transaction.Transactional;
import org.example.grupo20integrador3.dtos.CarreraSimpleDTO;
import org.example.grupo20integrador3.dtos.EstudianteCarreraRequestDTO;
import org.example.grupo20integrador3.dtos.EstudianteDTO;
import org.example.grupo20integrador3.entities.Carrera;
import org.example.grupo20integrador3.entities.Estudiante;
import org.example.grupo20integrador3.entities.EstudianteCarrera;
import org.example.grupo20integrador3.repositories.CarreraRepository;
import org.example.grupo20integrador3.repositories.EstudianteCarreraRepository;
import org.example.grupo20integrador3.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("EstudianteCarreraServicio")
public class EstudianteCarreraServicio {

    @Autowired
    private EstudianteCarreraRepository estudianteCarreraRepository;
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private CarreraRepository carreraRepository;
    @Autowired
    private EstudianteServicio estudianteServicio;

    @Transactional
    public EstudianteDTO save(EstudianteCarreraRequestDTO request) {

        int dni_estudiante = request.getDniAlumno();
        int idCarrera = request.getIdCarrera();

        // Verificar que existen el estudiante y la carrera
        Optional<Estudiante> estudianteOpt = estudianteRepository.findByDNI(dni_estudiante);
        Optional<Carrera> carreraOpt = carreraRepository.findByIdCarrera(idCarrera);

        if (estudianteOpt.isPresent() && carreraOpt.isPresent()) {
            EstudianteCarrera estudianteCarrera = new EstudianteCarrera(request.getInscripcion(), request.getGraduacion(), request.getAntiguedad(), estudianteOpt.get(), carreraOpt.get());

            // Verificar si ya existe la relación
            List<EstudianteCarrera> existingEstudianteCarrera = estudianteCarreraRepository.findByEstudianteAndCarrera(estudianteOpt.get(), carreraOpt.get());
            if (existingEstudianteCarrera.isEmpty()) {
                estudianteCarreraRepository.save(estudianteCarrera);
            } else {
                System.out.println("La relación ya existe para el estudiante con DNI: " + dni_estudiante + " y la carrera: " + carreraOpt.get().getNombre());
                return null;
            }

        }
        else {
            System.out.println("No se encontró estudiante o carrera con los IDs proporcionados.");
            return null;
        }

        CarreraSimpleDTO carreranueva= new CarreraSimpleDTO(carreraOpt.get().getNombre());
        ArrayList<CarreraSimpleDTO> carrerasInscriptas = estudianteServicio.getCarrerasInscriptas(estudianteOpt.get());
        carrerasInscriptas.add(carreranueva);

        return new EstudianteDTO(estudianteOpt.get().getNombre(),
                                 estudianteOpt.get().getApellido(),
                                 estudianteOpt.get().getEdad(),
                                 estudianteOpt.get().getGenero(),
                                 estudianteOpt.get().getDNI(),
                                 estudianteOpt.get().getCiudad(),
                                 estudianteOpt.get().getLU(),
                                 carrerasInscriptas);
    }
}

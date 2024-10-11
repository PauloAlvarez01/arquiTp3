package org.example.grupo20integrador3.services;

import jakarta.transaction.Transactional;
import org.example.grupo20integrador3.dtos.CarreraDTO;
import org.example.grupo20integrador3.dtos.CarreraSimpleDTO;
import org.example.grupo20integrador3.dtos.EstudianteDTO;
import org.example.grupo20integrador3.entities.Carrera;
import org.example.grupo20integrador3.entities.Estudiante;
import org.example.grupo20integrador3.entities.EstudianteCarrera;
import org.example.grupo20integrador3.repositories.CarreraRepository;
import org.example.grupo20integrador3.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service("EstudianteServicio")
public class EstudianteServicio /*VER QUE IMPLEMENTA*/ {

@Autowired
    private EstudianteRepository estudianteRepository;

//Experimental
@Autowired
    private CarreraRepository carreraRepository;


    @Transactional
    public List<EstudianteDTO> getEstudiantesOrdenados()throws Exception{

        var resultado = estudianteRepository.getEstudiantesOrdenApellido();


        try{

            return resultado.stream().map(estudiante->new EstudianteDTO(estudiante.getNombre(), estudiante.getApellido(),estudiante.getEdad(),estudiante.getGenero(),estudiante.getDNI(),estudiante.getCiudad(),estudiante.getLU(), getCarrerasInscriptas(estudiante))).collect(Collectors.toList());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    private ArrayList<CarreraSimpleDTO> getCarrerasInscriptas ( Estudiante estudiante){
        ArrayList<CarreraSimpleDTO> carrerasDTO = new ArrayList<>();
        ArrayList<EstudianteCarrera>  carreras = estudiante.getCarrerasInscriptas();
        for(EstudianteCarrera carrera : carreras) {
            Carrera carreraOpt = carreraRepository.findCarreraByIdCarrera(carrera.getId_carrera());
            carrerasDTO.add(new CarreraSimpleDTO(carreraOpt.getNombre()));
        }
        return carrerasDTO;
    }
}

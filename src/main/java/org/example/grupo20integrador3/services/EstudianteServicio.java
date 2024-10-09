package org.example.grupo20integrador3.services;

import jakarta.transaction.Transactional;
import org.example.grupo20integrador3.dtos.EstudianteDTO;
import org.example.grupo20integrador3.entities.Estudiante;
import org.example.grupo20integrador3.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service("EstudianteServicio")
public class EstudianteServicio /*VER QUE IMPLEMENTA*/ {

@Autowired
    private EstudianteRepository estudianteRepository;

    @Transactional
    public List<EstudianteDTO> getEstudiantesOrdenados()throws Exception{

        var resultado = estudianteRepository.getEstudiantesOrdenApellido();
        try{
            return resultado.stream().map(estudiante->new EstudianteDTO(estudiante.getNombre(), estudiante.getApellido(),estudiante.getEdad(),estudiante.getGenero(),estudiante.getDNI(),estudiante.getCiudad(),estudiante.getLU())).collect(Collectors.toList());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}

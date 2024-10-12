package org.example.grupo20integrador3.services;

import jakarta.transaction.Transactional;
import org.example.grupo20integrador3.dtos.CarreraDTO;
import org.example.grupo20integrador3.dtos.EstudianteDTO;
import org.example.grupo20integrador3.repositories.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("CarreraServicio")
public class CarreraServicio {

    @Autowired
    private CarreraRepository carreraRepository;

    @Transactional
    public List<CarreraDTO> getCarrerasConInscriptosOrdenadas()throws Exception{

        var resultado = carreraRepository.getCarrerasConInscriptosOrdenadas();

        try{
            return resultado.stream().map(carrera->new CarreraDTO(carrera, carreraRepository.findByNombre(carrera).get().getInscriptos())).collect(Collectors.toList());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}

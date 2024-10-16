package org.example.grupo20integrador3.services;

import jakarta.transaction.Transactional;
import org.example.grupo20integrador3.dtos.CarreraDTO;
import org.example.grupo20integrador3.dtos.CarreraSimpleDTO;
import org.example.grupo20integrador3.dtos.EstudianteDTO;
import org.example.grupo20integrador3.dtos.EstudianteRequestDTO;
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

    public ArrayList<CarreraSimpleDTO> getCarrerasInscriptas ( Estudiante estudiante){
        ArrayList<CarreraSimpleDTO> carrerasDTO = new ArrayList<>();
        ArrayList<EstudianteCarrera>  carreras = estudiante.getCarrerasInscriptas();
        for(EstudianteCarrera carrera : carreras) {
            Carrera carreraOpt = carreraRepository.findCarreraByIdCarrera(carrera.getId_carrera());
            carrerasDTO.add(new CarreraSimpleDTO(carreraOpt.getNombre()));
        }
        return carrerasDTO;
    }


    /*  ***************** PARA CONSULTAR EN CLASE ******************************
        var   carreras = estudiante.getCarrerasInscriptas();
        try{
            return carreras.stream().map(carrera -> new CarreraSimpleDTO(carrera.getNombre()).collect(Collectors.toList()));

        }
        catch{}

     */

    @Transactional
    public EstudianteDTO save(EstudianteRequestDTO request) {
        final var estudiante = new Estudiante( request );
        final var result = this.estudianteRepository.save( estudiante );
        return new EstudianteDTO( result.getNombre(), result.getApellido(), result.getEdad(), result.getGenero(), result.getDNI(), result.getCiudad(), result.getLU(), null);
    }

    @Transactional
    public EstudianteDTO findByLu(int lu) {
        var resultado = estudianteRepository.findByLU(lu);
        if(resultado.isPresent()) {
            return new EstudianteDTO(resultado.get().getNombre(),
                                    resultado.get().getApellido(),
                                    resultado.get().getEdad(),
                                    resultado.get().getGenero(),
                                    resultado.get().getDNI(),
                                    resultado.get().getCiudad(),
                                    resultado.get().getLU(),
                                    getCarrerasInscriptas(resultado.get()));
        }
        return null;
    }

    public List<EstudianteDTO> findByGenero(String genero) throws Exception {
        var resultado = estudianteRepository.findEstudiantesByGenero(genero);

        try{
            return resultado.stream().map(estudiante->new EstudianteDTO(estudiante.getNombre(), estudiante.getApellido(),estudiante.getEdad(),estudiante.getGenero(),estudiante.getDNI(),estudiante.getCiudad(),estudiante.getLU(), getCarrerasInscriptas(estudiante))).collect(Collectors.toList());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<EstudianteDTO> findEstudiantesByCarreraAndLocalidad(String carrera, String localidad) throws Exception {
        var resultado = estudianteRepository.findEstudiantesByCarreraAndLocalidad(carrera, localidad);
        try{
            return resultado.stream().map(estudiante->new EstudianteDTO(estudiante.getNombre(), estudiante.getApellido(),estudiante.getEdad(),estudiante.getGenero(),estudiante.getDNI(),estudiante.getCiudad(),estudiante.getLU(), getCarrerasInscriptas(estudiante))).collect(Collectors.toList());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public int delete(Long lu) {
        try{
            if(estudianteRepository.existsById(lu)){
                estudianteRepository.deleteById(lu);
                return 1;
            }else{
                return 0;
            }
        }catch (Exception e){
            return 99;
        }
    }
}

package org.example.grupo20integrador3.controllers;

import lombok.RequiredArgsConstructor;
import org.example.grupo20integrador3.dtos.EstudianteDTO;
import org.example.grupo20integrador3.dtos.EstudianteRequestDTO;
import org.example.grupo20integrador3.entities.Estudiante;
import org.example.grupo20integrador3.repositories.EstudianteRepository;
import org.example.grupo20integrador3.services.EstudianteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/estudiantes")

    public class EstudianteController {

        @Autowired
        private EstudianteServicio estudianteServicio;

        public EstudianteController(EstudianteServicio estudianteServicio) {
            this.estudianteServicio = estudianteServicio;
        }

        /*a) dar de alta un estudiante
         */
        @PostMapping("")
        public ResponseEntity<EstudianteDTO> save( @RequestBody EstudianteRequestDTO request ){
            final var result = this.estudianteServicio.save(request);
            return ResponseEntity.ok().body( result );
        }

        /*c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
         */
        @GetMapping("/ordenadosApellido")
        public Iterable<EstudianteDTO> ordenadosApellido() throws Exception {
            return estudianteServicio.getEstudiantesOrdenados();
        }


        /*d) recuperar un estudiante, en base a su número de libreta universitaria.
         */
        @GetMapping("/lu/{lu}")
        public EstudianteDTO findEstudianteByLu( @PathVariable int lu ){
            return this.estudianteServicio.findByLu(lu);
        }

        /*e) recuperar todos los estudiantes, en base a su género.
         */
        @GetMapping("/genero/{genero}")
        public Iterable<EstudianteDTO> findEstudiantesByGenero(@PathVariable String genero ) throws Exception {
            return this.estudianteServicio.findByGenero(genero);
        }

        /*g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
         */
        @GetMapping("/{carrera}/{localidad}")
        public Iterable<EstudianteDTO> findEstudiantesByCarreraAndLocalidad(@PathVariable String carrera , @PathVariable String localidad) throws Exception {
            return this.estudianteServicio.findEstudiantesByCarreraAndLocalidad(carrera, localidad);
        }

    @DeleteMapping("/lu/{lu}")
    public ResponseEntity<String> delete( @PathVariable Long lu ) throws Exception {
        String message = "";
        var result = this.estudianteServicio.delete(lu);
        if (result  == 1){
            message = "Se eliminó con exito el estudiante con LU: " + lu.toString();
            return ResponseEntity.ok().body( message );
        } else if (result == 0){
            return ResponseEntity.notFound().build();
        }
        else {
            message = "El estudiante con LU: " + lu.toString() + " se encuentra matriculado en alguna carrera. No se puede eliminar.";
            return ResponseEntity.internalServerError().body( message );
        }
    }
}

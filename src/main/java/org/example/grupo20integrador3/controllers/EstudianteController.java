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


@RestController
@RequestMapping("/estudiantes")

    public class EstudianteController {

        @Autowired
        private EstudianteServicio estudianteServicio;

        public EstudianteController(EstudianteServicio estudianteServicio) {
            this.estudianteServicio = estudianteServicio;
        }

        /*c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
         */
        @GetMapping("/ordenadosApellido")
        public Iterable<EstudianteDTO> ordenadosApellido() throws Exception {
            return estudianteServicio.getEstudiantesOrdenados();
        }

        /*a) dar de alta un estudiante
        */
        @PostMapping("")
        public ResponseEntity<EstudianteDTO> save( @RequestBody EstudianteRequestDTO request ){
            final var result = this.estudianteServicio.save(request);
            return ResponseEntity.accepted().body( result );
        }

        /*d) recuperar un estudiante, en base a su número de libreta universitaria.
         */
        @GetMapping("/{lu}")
        public EstudianteDTO findEstudianteByLu( @PathVariable int lu ){
            return this.estudianteServicio.findByLu(lu);
        }
}

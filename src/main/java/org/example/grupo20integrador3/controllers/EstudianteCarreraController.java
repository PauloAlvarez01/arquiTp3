package org.example.grupo20integrador3.controllers;

import lombok.RequiredArgsConstructor;
import org.example.grupo20integrador3.dtos.EstudianteDTO;
import org.example.grupo20integrador3.services.EstudianteCarreraServicio;
import org.example.grupo20integrador3.dtos.EstudianteCarreraRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estudiantes")

    public class EstudianteCarreraController {

        @Autowired
        private  EstudianteCarreraServicio estudianteCarreraServicio;

        public EstudianteCarreraController(EstudianteCarreraServicio estudianteCarreraServicio) {
            this.estudianteCarreraServicio = estudianteCarreraServicio;
        }

        /*b) matricular un estudiante en una carrera
        */
        @PostMapping("/matricular")
        public ResponseEntity<EstudianteDTO> save(@RequestBody EstudianteCarreraRequestDTO request ){
            final var result = this.estudianteCarreraServicio.save( request );
            return ResponseEntity.accepted().body( result );
        }


    }

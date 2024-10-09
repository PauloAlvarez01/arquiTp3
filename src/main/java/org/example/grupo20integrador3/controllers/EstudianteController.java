package org.example.grupo20integrador3.controllers;

import org.example.grupo20integrador3.dtos.EstudianteDTO;
import org.example.grupo20integrador3.entities.Estudiante;
import org.example.grupo20integrador3.repositories.EstudianteRepository;
import org.example.grupo20integrador3.services.EstudianteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
}

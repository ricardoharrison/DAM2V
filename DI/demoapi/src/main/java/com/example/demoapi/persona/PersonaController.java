package com.example.demoapi.persona;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController // publica los endpoints (urls con peticiones)
@RequestMapping("/persona")

@RequiredArgsConstructor
public class PersonaController {

    private final PersonaService personaService;

    @PostMapping("/crear") // http://localhost:8080/persona/crear
    public void insertarPersona(@RequestBody Persona persona) {
        personaService.crearPersona(persona);
    }

}
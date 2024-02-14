package com.example.demoapi.persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    public void crearPersona(Persona persona) {
        personaRepository.save(persona);
    }
}

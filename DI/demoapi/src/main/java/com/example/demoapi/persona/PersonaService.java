package com.example.demoapi.persona;

import java.util.List;
import java.util.Optional;

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

    public void borrarPersona(Integer id) {
        personaRepository.deleteById(id);
    }

    public Optional<Persona> obtenerPersona(Integer id) {
        return personaRepository.findById(id);
    }

    public List<Persona> obtenerTodas() {
        return personaRepository.findAll();
    }
}

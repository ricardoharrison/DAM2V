package com.example.trabajospring.rharrison;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RHarrisonService {
    @Autowired
    private RHarrisonRepo personaRepository;

    public void crearPersona(RHarrison persona) {
        personaRepository.save(persona);
    }

    public boolean borrarPersona(Integer id) {
        if (personaRepository.findById(id).isPresent()) {
            personaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<RHarrison> obtenerPersona(Integer id) {
        return personaRepository.findById(id);
    }

    public List<RHarrison> obtenerTodas() {
        return personaRepository.findAll();
    }

    public Optional<RHarrison> actualizarPersona(Integer id, RHarrison persona) {
        Optional<RHarrison> p = personaRepository.findById(id);
        if (p.isPresent()) {
            p.get().setNombre(persona.getNombre());
            p.get().setApellidos(persona.getApellidos());
            p.get().setEmail(persona.getEmail());
            personaRepository.saveAndFlush(p.get());
        }
        return p;
    }
}

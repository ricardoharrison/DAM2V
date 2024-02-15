package com.example.demoapi.persona;

import org.springframework.web.bind.annotation.RestController;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController // publica los endpoints (urls con peticiones)
@RequestMapping("/persona")

@RequiredArgsConstructor
public class PersonaController {

    @Autowired

    private final PersonaService personaService;

    @PostMapping("crear") // http://localhost:8080/persona/crear
    public ResponseEntity<String> insertarPersona(@RequestBody Persona persona) {
        personaService.crearPersona(persona);
        return ResponseEntity.ok("Se ha creado la persona con éxito"); // para dar una respuesta al usuario que hace la
                                                                       // petición
    }

    @PutMapping("borrarPersona/{id}")
    public ResponseEntity<String> borrarPersona(@PathVariable String id) {
        // TODO: process PUT request
        personaService.borrarPersona(Integer.parseInt(id));
        return ResponseEntity.ok("Se ha borrado la persona con éxito");
    }

    @GetMapping("obtenerPersona/{id}")
    public ResponseEntity<Optional<Persona>> obtenerPersona(@PathVariable String id) {
        return ResponseEntity.ok(personaService.obtenerPersona(Integer.parseInt(id)));
    }

    @GetMapping("obtenerTodas")
    public ResponseEntity<List<Persona>> obtenerTodas() {
        return ResponseEntity.ok(personaService.obtenerTodas());
    }

}
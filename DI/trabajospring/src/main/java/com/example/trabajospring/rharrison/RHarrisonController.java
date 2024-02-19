package com.example.trabajospring.rharrison;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController // publica los endpoints (urls con peticiones)
@RequestMapping("/persona")

@RequiredArgsConstructor
public class RHarrisonController {

    private final RHarrisonService personaService;

    @PostMapping("/crear") // http://localhost:8080/persona/crear
    public void insertarPersona(@RequestBody RHarrison persona) {
        personaService.crearPersona(persona);
    }

    @PutMapping("borrarPersona/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Persona creada correctamente"),
            @ApiResponse(responseCode = "201", description = "ERROR: persona no creada"),
    })
    public ResponseEntity<String> borrarPersona(@PathVariable String id) {
        if (personaService.borrarPersona(Integer.parseInt(id))) {
            return ResponseEntity.ok("Se ha borrado la persona correctamente");
        } else {
            return ResponseEntity.status(201).body("ERROR: no se ha realizado el registro, no estaba en la DB");
        }
    }

    @GetMapping("obtenerPersona/{id}")
    public ResponseEntity<Optional<RHarrison>> obtenerPersona(
            @PathVariable String id) {
        return ResponseEntity.ok(
                personaService.obtenerPersona(Integer.parseInt(id)));
    }

    @GetMapping("obtenerTodas")
    public ResponseEntity<List<RHarrison>> obtenerTodas() {
        return ResponseEntity.ok(personaService.obtenerTodas());
    }

    @PostMapping("actualizarPersona/{id}") // http://localhost:8080/persona/actualizarPersona
    public ResponseEntity<Optional<RHarrison>> actualizarPersona(
            @PathVariable String id,
            @RequestBody RHarrison persona) {
        personaService.actualizarPersona(Integer.parseInt(id), persona);

        return ResponseEntity.ok(
                personaService.actualizarPersona(Integer.parseInt(id), persona));
    }
}

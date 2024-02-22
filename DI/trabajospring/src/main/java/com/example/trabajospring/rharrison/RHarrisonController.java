package com.example.trabajospring.rharrison;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController // publica los endpoints (urls con peticiones)
@RequestMapping("/rHarrison")

@RequiredArgsConstructor
public class RHarrisonController {

    private final RHarrisonService rHarrisonService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "MiniYo creado con éxito"),
            @ApiResponse(responseCode = "205", description = "MiniYo creado con información parcial"),
    })
    @PostMapping("/crear") // http://localhost:8080/rHarrison/crear
    public ResponseEntity<String> crearMiniYo(@RequestBody RHarrison rHarrison) {
        return rHarrisonService.crearMiniYo(rHarrison);
    }

    @PutMapping("borrar/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "MiniYo eliminado"),
            @ApiResponse(responseCode = "209", description = "MiniYo no eliminado porque no existe"),
    })
    public ResponseEntity<String> borrarMiniYo(@PathVariable String id) {
        return rHarrisonService.borrarMiniYo(Integer.parseInt(id));
    }

    @GetMapping("buscar/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "MiniYo encontrado"),
            @ApiResponse(responseCode = "209", description = "No existe MiniYo con ese id"),
    })
    public ResponseEntity<?> buscarMiniYo(@PathVariable String id) {
        Optional<RHarrison> miniYo = rHarrisonService.buscarMiniYo(Integer.parseInt(id));
        if (miniYo.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(miniYo);
        } else {
            return ResponseEntity.status(209).body("No existe MiniYo con ese id");
        }
    }

    @GetMapping("todosLosMiniYo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Todos los MiniYos son mostrados correctamente"),
            @ApiResponse(responseCode = "209", description = "No hay ningún MiniYo para mostrar"),
    })
    public ResponseEntity<?> obtenerTodosLosMiniYo() {
        List<RHarrison> miniYos = rHarrisonService.obtenerTodosLosMiniYo();
        if (!miniYos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(miniYos);
        } else {
            return ResponseEntity.status(209).body("No hay ningún MiniYo para mostrar");
        }
    }

    @PostMapping("editar/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "MiniYo actualizado con éxito"),
            @ApiResponse(responseCode = "201", description = "MiniYo actualizado parcialmente con éxito"),
            @ApiResponse(responseCode = "209", description = "No se ha encontrado ese MiniYo"),
    })
    public ResponseEntity<?> editarMiniYo(
            @PathVariable String id,
            @RequestBody RHarrison rHarrison) {
        Optional<RHarrison> miniYo = rHarrisonService.editarMiniYo(Integer.parseInt(id), rHarrison);
        if (miniYo.isPresent()) {
            if (miniYo.get().equals(rHarrison)) {
                return ResponseEntity.status(HttpStatus.OK).body(miniYo);
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(miniYo);
            }
        } else {
            return ResponseEntity.status(209).body("No se ha encontrado ese MiniYo");
        }
    }

    @PostMapping("aumentarSaldo/{id}/{cantidad}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Saldo aumentado correctamente"),
            @ApiResponse(responseCode = "201", description = "Saldo inicializado y aumentado con éxito"),
            @ApiResponse(responseCode = "209", description = "No existe ese ID"),
    })
    public ResponseEntity<String> aumentarSaldo(
            @PathVariable Integer id,
            @PathVariable float cantidad) {
        return rHarrisonService.aumentarSaldo(id, cantidad);
    }

    @PostMapping("reducirSaldo/{id}/{cantidad}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Saldo devengado correctamente y positivo"),
            @ApiResponse(responseCode = "201", description = "Saldo devengado y negativo"),
            @ApiResponse(responseCode = "209", description = "No existe ese ID"),
    })
    public ResponseEntity<String> reducirSaldo(
            @PathVariable Integer id,
            @PathVariable float cantidad) {
        return rHarrisonService.reducirSaldo(id, cantidad);
    }

    @GetMapping("mediaSaldos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Saldo medio de todos los registros"),
            @ApiResponse(responseCode = "201", description = "El saldo medio es negativo"),
            @ApiResponse(responseCode = "404", description = "No hay ningún MiniYo almacenado todavía"),
    })
    public ResponseEntity<String> mediaSaldos() {
        return rHarrisonService.mediaSaldos();
    }

    @DeleteMapping("borrarTodo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Todos los elementos se han eliminado correctamente"),
    })
    public ResponseEntity<String> borrarTodo() {
        return rHarrisonService.borrarTodo();
    }

}

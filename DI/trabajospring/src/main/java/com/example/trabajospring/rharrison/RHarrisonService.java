package com.example.trabajospring.rharrison;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RHarrisonService {
    @Autowired
    private RHarrisonRepo rHarrisonRepo;

    public ResponseEntity<String> crearMiniYo(RHarrison rHarrison) {
        if (rHarrison.getNombre() != null && rHarrison.getEdad() != null && rHarrison.getSaldo() != null
                && rHarrison.getHabilidad() != null) {
            rHarrisonRepo.save(rHarrison);
            return ResponseEntity.status(HttpStatus.OK).body("Elemento creado con éxito");
        } else {
            rHarrisonRepo.save(rHarrison);
            return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("Elemento creado con información parcial");
        }
    }

    public ResponseEntity<String> borrarMiniYo(Integer id) {
        if (rHarrisonRepo.existsById(id)) {
            rHarrisonRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("MiniYo eliminado");
        } else {
            return ResponseEntity.status(209).body("MiniYo no eliminado porque no existe");
        }
    }

    public Optional<RHarrison> buscarMiniYo(Integer id) {
        return rHarrisonRepo.findById(id);
    }

    public List<RHarrison> obtenerTodosLosMiniYo() {
        List<RHarrison> todosLosMiniYos = rHarrisonRepo.findAll();
        return todosLosMiniYos;
    }

    public Optional<RHarrison> editarMiniYo(Integer id, RHarrison rHarrison) {
        Optional<RHarrison> r = rHarrisonRepo.findById(id);
        if (r.isPresent()) {
            r.get().setNombre(rHarrison.getNombre());
            r.get().setEdad(rHarrison.getEdad());
            r.get().setHabilidad(rHarrison.getHabilidad());
            r.get().setSaldo(rHarrison.getSaldo());
            rHarrisonRepo.saveAndFlush(r.get());
        }
        return r;
    }

    public ResponseEntity<String> aumentarSaldo(Integer id, float cantidad) {
        Optional<RHarrison> miniYoContento = rHarrisonRepo.findById(id);
        if (miniYoContento.isPresent()) {
            RHarrison rharrison = miniYoContento.get();
            Float saldoActual = rharrison.getSaldo();
            if (saldoActual == null) {
                saldoActual = 0.0f;
            }
            float nuevoSaldo = saldoActual + cantidad;
            rharrison.setSaldo(nuevoSaldo);
            rHarrisonRepo.save(rharrison);
            if (saldoActual == 0.0f) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Saldo inicializado y aumentado con éxito");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body("Saldo aumentado correctamente");
            }
        } else {
            return ResponseEntity.status(209).body("No existe ese ID");
        }
    }

    public ResponseEntity<String> reducirSaldo(Integer id, float cantidad) {
        Optional<RHarrison> miniYoTriste = rHarrisonRepo.findById(id);
        if (miniYoTriste.isPresent()) {
            RHarrison rharrison = miniYoTriste.get();
            float saldoActual = rharrison.getSaldo();
            float nuevoSaldo = saldoActual - cantidad;
            rharrison.setSaldo(nuevoSaldo);
            rHarrisonRepo.save(rharrison);
            if (nuevoSaldo >= 0) {
                return ResponseEntity.status(HttpStatus.OK).body("Saldo devengado correctamente y positivo");
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body("Saldo devengado y negativo");
            }
        } else {
            return ResponseEntity.status(209).body("No existe ese ID");
        }
    }

    public ResponseEntity<String> mediaSaldos() {
        List<RHarrison> todosLosMiniYo = rHarrisonRepo.findAll();
        if (!todosLosMiniYo.isEmpty()) {
            float sumaSaldos = 0;
            int contador = 0;
            for (RHarrison rharrison : todosLosMiniYo) {
                Float saldo = rharrison.getSaldo();
                if (saldo != null) {
                    sumaSaldos += saldo;
                    contador++;
                }
            }
            float media = sumaSaldos / contador;
            if (media >= 0) {
                return ResponseEntity.status(HttpStatus.OK).body("Saldo medio de todos los MiniYo: " + media);
            } else {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Los MiniYo están muy trsites porque el saldo medio es negativo");
            }
        } else {
            // hace falta porque si no el método dice que debe devolver un Entity
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay ningún MiniYo almacenado todavía");
        }
    }

    public ResponseEntity<String> borrarTodo() {
        rHarrisonRepo.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("Todos los miniYo se han eliminado correctamente");
    }

}

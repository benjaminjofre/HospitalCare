package com.hospital_vm_vl.hospital_vm.Examenes.controller;

import com.hospital_vm_vl.hospital_vm.Examenes.model.Examenes;
import com.hospital_vm_vl.hospital_vm.Examenes.service.ExamenesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Examenes")
@Tag(name = "Examenes", description = "Operaciones realcionadas con los Examenes")
public class ExamenesController {

    @Autowired
    private ExamenesService examenesService;

    @GetMapping
    @Operation(summary = "Obtener Examenes", description = "obtiene una lista de los Examenes")
    public ResponseEntity<List<Examenes>> listar() {
        List<Examenes> examenes = examenesService.findAll();
        if (examenes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(examenes);
    }

    @PostMapping
    @Operation(summary = "insertar Examenes", description = "inserta una lista de los Examenes")
    public ResponseEntity<Examenes> guardar(@RequestBody Examenes examenes) {
        try {
            Examenes nuevoPaciente = examenesService.save(examenes);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPaciente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Examenes por id", description = "obtiene una lista de los Examenes")
    public ResponseEntity<Examenes> buscar(@PathVariable Long id) {
        try {
            Examenes paciente = examenesService.findById(id);
            return ResponseEntity.ok(paciente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation (summary = "actualizar Examenes", description = "actualiza una lista de los Examenes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Examen actualizado con exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Examenes.class))),
            @ApiResponse(responseCode = "404", description = "Examen no encontrad")
    })
    public ResponseEntity<Examenes> actualizar(@PathVariable Long id, @RequestBody Examenes examenes) {
        try {
            Examenes pacExistente = examenesService.findById(id);

            pacExistente.setPacienteId(examenes.getPacienteId());
            pacExistente.setMedicosId(examenes.getMedicosId());
            pacExistente.setNombre(examenes.getNombre());

            examenesService.save(pacExistente);
            return ResponseEntity.ok(pacExistente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Examenes", description = "Elimina un Examen de la lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Examen eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Examen no encontrada")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            examenesService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

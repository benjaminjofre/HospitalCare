package com.hospital_vm_vl.hospital_vm.Urgencias.controller;

import com.hospital_vm_vl.hospital_vm.Urgencias.model.Urgencias;
import com.hospital_vm_vl.hospital_vm.Urgencias.service.UrgenciasService;
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
@RequestMapping("/api/v1/Urgencias")
@Tag(name = "Urgencias", description = "Operaciones realcionadas con las Urgencias")
public class UrgenciasController {

    @Autowired
    private UrgenciasService urgenciasService;

    @GetMapping
    @Operation(summary = "Obtener Urgencias", description = "obtiene una lista de las Urgencias")
    public ResponseEntity<List<Urgencias>> listar() {
        List<Urgencias> urgencias = urgenciasService.findAll();
        if (urgencias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(urgencias);
    }

    @PostMapping
    @Operation(summary = "insertar Urgencias", description = "inserta una lista de las Urgencias")
    public ResponseEntity<Urgencias> guardar(@RequestBody Urgencias urgencias) {
        try {
            Urgencias nuevoUrgencias = urgenciasService.save(urgencias);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUrgencias);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Urgencias por id", description = "obtiene una lista de las Urgencias")
    public ResponseEntity<Urgencias> buscar(@PathVariable Long id) {
        try {
            Urgencias urgencias = urgenciasService.findById(id);
            return ResponseEntity.ok(urgencias);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation (summary = "actualizar Urgencias", description = "actualiza una lista de las Urgencias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Urgencia actualizado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Urgencias.class))),
            @ApiResponse(responseCode = "404", description = "Urgencia no encontrado")
    })
    public ResponseEntity<Urgencias> actualizar(@PathVariable Long id, @RequestBody Urgencias urgencias) {
        try {
            Urgencias pacExistente = urgenciasService.findById(id);

            pacExistente.setPacienteId(urgencias.getPacienteId());
            pacExistente.setMedicosId(urgencias.getMedicosId());
            pacExistente.setEstadoPaciente(urgencias.getEstadoPaciente());

            urgenciasService.save(pacExistente);
            return ResponseEntity.ok(pacExistente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Urgencia", description = "Elimina un Urgencia de la lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Urgencia eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Urgencia no encontrado")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            urgenciasService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.hospital_vm_vl.hospital_vm.Especialidades.controller;

import com.hospital_vm_vl.hospital_vm.Especialidades.model.Especialidades;
import com.hospital_vm_vl.hospital_vm.Especialidades.service.EspecialidadesService;
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
@RequestMapping("/api/v1/Especialidades")
@Tag(name = "Especialidades", description = "Operaciones realcionadas con las Especialidades")
public class EspecialidadesController {

    @Autowired
    private EspecialidadesService especialidadesService;

    @GetMapping
    @Operation(summary = "Obtener Especialidades", description = "obtiene una lista de las Especialidades")
    public ResponseEntity<List<Especialidades>> listar() {
        List<Especialidades> especialidades = especialidadesService.findAll();
        if (especialidades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(especialidades);
    }

    @PostMapping
    @Operation(summary = "insertar Especialidades", description = "inserta una lista de las Especialidades")
    public ResponseEntity<Especialidades> guardar(@RequestBody Especialidades especialidades) {
        try {
            Especialidades nuevoEspecialidades = especialidadesService.save(especialidades);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEspecialidades);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Especialidades por id", description = "obtiene una lista de las Especialidades")
    public ResponseEntity<Especialidades> buscar(@PathVariable Long id) {
        try {
            Especialidades especialidades = especialidadesService.findById(id);
            return ResponseEntity.ok(especialidades);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation (summary = "actualizar Especialidades", description = "actualiza una lista de las Especialidades")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Especialidad actualizada con exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Especialidades.class))),
            @ApiResponse(responseCode = "404", description = "Especialidad no encontrada")
    })
    public ResponseEntity<Especialidades> actualizar(@PathVariable Long id, @RequestBody Especialidades especialidades) {
        try {
            Especialidades pacExistente = especialidadesService.findById(id);

            pacExistente.setNombre(especialidades.getNombre());
            pacExistente.setMedicosId(especialidades.getMedicosId());

            especialidadesService.save(pacExistente);
            return ResponseEntity.ok(pacExistente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation (summary = "Eliminar Especialidades", description = "Elimina una Especialidad de la lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Especialidad eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Especialidad no encontrada")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            especialidadesService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

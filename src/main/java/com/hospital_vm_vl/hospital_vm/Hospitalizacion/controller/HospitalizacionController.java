package com.hospital_vm_vl.hospital_vm.Hospitalizacion.controller;


import com.hospital_vm_vl.hospital_vm.Hospitalizacion.model.Hospitalizacion;
import com.hospital_vm_vl.hospital_vm.Hospitalizacion.service.HospitalizacionService;
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
@RequestMapping("/api/v1/Hospitalizacion")
@Tag(name = "Hospitalizacion", description = "Operaciones realcionadas con la Hospitalizacion")
public class HospitalizacionController {

    @Autowired
    private HospitalizacionService hospitalizacionService;

    @GetMapping
    @Operation(summary = "Obtener Hospitalizacion", description = "obtiene una lista de la Hospitalizacion")
    public ResponseEntity<List<Hospitalizacion>> listar() {
        List<Hospitalizacion> hospitalizacion = hospitalizacionService.findAll();
        if (hospitalizacion.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(hospitalizacion);
    }

    @PostMapping
    @Operation(summary = "insertar Hospitalizacion", description = "inserta una lista de la Hospitalizacion")
    public ResponseEntity<Hospitalizacion> guardar(@RequestBody Hospitalizacion hospitalizacion) {
        try {
            Hospitalizacion nuevoHospitalizacion = hospitalizacionService.save(hospitalizacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoHospitalizacion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Hospitalizacion por id", description = "obtiene una lista de la Hospitalizacion")
    public ResponseEntity<Hospitalizacion> buscar(@PathVariable Long id) {
        try {
            Hospitalizacion hospitalizacion = hospitalizacionService.findById(id);
            return ResponseEntity.ok(hospitalizacion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation (summary = "actualizar Hospitalizacion", description = "actualizar una lista de la Hospitalizacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospitalizacion actualizada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Hospitalizacion.class))),
            @ApiResponse(responseCode = "404", description = "Hospitalizacion no encontrad")
    })
    public ResponseEntity<Hospitalizacion> actualizar(@PathVariable Long id, @RequestBody Hospitalizacion hospitalizacion) {
        try {
            Hospitalizacion pacExistente = hospitalizacionService.findById(id);

            pacExistente.setPacienteId(hospitalizacion.getPacienteId());
            pacExistente.setEstadoPaciente(hospitalizacion.getEstadoPaciente());

            hospitalizacionService.save(pacExistente);
            return ResponseEntity.ok(pacExistente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Hospitalizacion", description = "Elimina una Hospitalizacion de la lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospitalizacion eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Hospitalizacion no encontrada")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            hospitalizacionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

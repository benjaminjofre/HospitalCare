package com.hospital_vm_vl.hospital_vm.FichasClinicas.controller;


import com.hospital_vm_vl.hospital_vm.FichasClinicas.service.FichasClinicasService;
import com.hospital_vm_vl.hospital_vm.FichasClinicas.model.FichasClinicas;
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
@RequestMapping("/api/v1/FichasClinicas")
@Tag(name = "FichasClinicas", description = "Operaciones realcionadas con las FichasClinicas")
public class FichasClinicasController {

    @Autowired
    private FichasClinicasService fichasClinicasService;

    @GetMapping
    @Operation(summary = "Obtener Fichas Clinicas", description = "obtiene una lista de los Fichas Clinicas")
    public ResponseEntity<List<FichasClinicas>> listar() {
        List<FichasClinicas> fichasClinicas = fichasClinicasService.findAll();
        if (fichasClinicas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(fichasClinicas);
    }

    @PostMapping
    @Operation(summary = "insertar Ficha Clinica", description = "inserta una lista de las Fichas Clinicas")
    public ResponseEntity<FichasClinicas> guardar(@RequestBody FichasClinicas fichasClinicas) {
        try {
            FichasClinicas nuevofichasClinicas = fichasClinicasService.save(fichasClinicas);
            return ResponseEntity.status(HttpStatus.CREATED).body(fichasClinicas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Ficha Clinica por id", description = "obtiene una lista de las Ficha Clinica ")
    public ResponseEntity<FichasClinicas> buscar(@PathVariable Long id) {
        try {
            FichasClinicas fichasClinicas = fichasClinicasService.findById(id);
            return ResponseEntity.ok(fichasClinicas);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation (summary = "actualizar FichasClinicas", description = "actualizar una lista de las FichasClinicas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "fichaClinica actualizada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FichasClinicas.class))),
            @ApiResponse(responseCode = "404", description = "fichaClinica no encontrada")
    })
    public ResponseEntity<FichasClinicas> actualizar(@PathVariable Long id, @RequestBody FichasClinicas fichasClinicas) {
        try {
            FichasClinicas pacExistente = fichasClinicasService.findById(id);

            pacExistente.setPacienteId(fichasClinicas.getPacienteId());
            pacExistente.setMedicosId(fichasClinicas.getMedicosId());

            fichasClinicasService.save(pacExistente);
            return ResponseEntity.ok(pacExistente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar FichasClinicas", description = "Elimina una fichaclinica de la lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "fichaClinica eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "fichaClinica no encontrado")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            fichasClinicasService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.hospital_vm_vl.hospital_vm.Medicos.controller;

import com.hospital_vm_vl.hospital_vm.Medicos.model.Medicos;
import com.hospital_vm_vl.hospital_vm.Medicos.service.MedicosService;
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
@RequestMapping("/api/v1/Medicos")
@Tag(name = "Medicos", description = "Operaciones realcionadas con los Medicos")
public class MedicosController {

    @Autowired
    private MedicosService medicosService;

    @GetMapping
    @Operation(summary = "Obtener Medicos", description = "obtiene una lista de los Medicos")
    public ResponseEntity<List<Medicos>> listar() {
        List<Medicos> medicos = medicosService.findAll();
        if (medicos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medicos);
    }


    @PostMapping
    @Operation(summary = "insertar Medicos", description = "inserta una lista de los Medicos")
    public ResponseEntity<Medicos> guardar(@RequestBody Medicos medicos) {
        try {
            Medicos nuevoMedicos = medicosService.save(medicos);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMedicos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Medicos por id", description = "obtiene una lista de los Medicos")
    public ResponseEntity<Medicos> buscar(@PathVariable Long id) {
        try {
            Medicos medicos = medicosService.findById(id);
            return ResponseEntity.ok(medicos);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation (summary = "actualizar Medicos", description = "actualiza una lista de los Medicos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medicos actualizado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medicos.class))),
            @ApiResponse(responseCode = "404", description = "Medico no encontrado")
    })
    public ResponseEntity<Medicos> actualizar(@PathVariable Long id, @RequestBody Medicos medicos) {
        try {
            Medicos pacExistente = medicosService.findById(id);

            pacExistente.setRun(medicos.getRun());
            pacExistente.setNombres(medicos.getNombres());
            pacExistente.setApellidos(medicos.getApellidos());
            pacExistente.setFechaNacimiento(medicos.getFechaNacimiento());
            pacExistente.setCorreo(medicos.getCorreo());

            medicosService.save(pacExistente);
            return ResponseEntity.ok(pacExistente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Medicos", description = "Elimina un Medico de la lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medico eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Medico no encontrado")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            medicosService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

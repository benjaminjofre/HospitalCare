package com.hospital_vm_vl.hospital_vm.paciente.controller;

import com.hospital_vm_vl.hospital_vm.cita.model.Cita;
import com.hospital_vm_vl.hospital_vm.paciente.model.Paciente;
import com.hospital_vm_vl.hospital_vm.paciente.service.PacienteService;
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
@RequestMapping("/api/v1/Pacientes")
@Tag(name = "Pacientes", description = "Operaciones realcionadas con los Pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    @Operation(summary = "Obtener Pacientes", description = "obtiene una lista de los Pacientes")
    public ResponseEntity<List<Paciente>> listar() {
        List<Paciente> pacientes = pacienteService.findAll();
        if (pacientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pacientes);
    }

    @PostMapping
    @Operation(summary = "insertar Paciente", description = "inserta una lista de los Paciente")
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) {
        try {
            Paciente nuevoPaciente = pacienteService.save(paciente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPaciente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Pacientes por id", description = "obtiene una lista de los Pacientes")
    public ResponseEntity<Paciente> buscar(@PathVariable Long id) {
        try {
            Paciente paciente = pacienteService.findById(id);
            return ResponseEntity.ok(paciente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation (summary = "actualizar Pacientes", description = "actualizar una lista de los Pacientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pacientes actualizado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cita.class))),
            @ApiResponse(responseCode = "404", description = "Paciente no encontrado")
    })
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @RequestBody Paciente paciente) {
        try {
            Paciente pacExistente = pacienteService.findById(id);

            pacExistente.setRun(paciente.getRun());
            pacExistente.setNombres(paciente.getNombres());
            pacExistente.setApellidos(paciente.getApellidos());
            pacExistente.setFechaNacimiento(paciente.getFechaNacimiento());
            pacExistente.setCorreo(paciente.getCorreo());

            pacienteService.save(pacExistente);
            return ResponseEntity.ok(pacExistente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Pacientes", description = "Elimina un Paciente de la lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Paciente no encontrado")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            pacienteService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
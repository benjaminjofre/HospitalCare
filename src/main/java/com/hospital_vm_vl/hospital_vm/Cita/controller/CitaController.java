package com.hospital_vm_vl.hospital_vm.Cita.controller;

import com.hospital_vm_vl.hospital_vm.Cita.model.Cita;
import com.hospital_vm_vl.hospital_vm.Cita.service.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Citas")
@Tag(name = "Citas", description = "Operaciones realcionadas con las Citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    @Operation (summary = "Obtener citas", description = "obtiene una lista de las citas")
    public ResponseEntity<List<Cita>> listar() {
        List<Cita> citas = citaService.findAll();
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }

    @PostMapping
    @Operation(summary = "insertar Cita", description = "inserta una lista de las Citas")
    public ResponseEntity<Cita> guardar(@Valid @RequestBody Cita cita) {
        try {
            Cita nuevaCita = citaService.save(cita);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCita);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    @Operation (summary = "Obtener citas por id", description = "obtiene una lista de las citas")
    public ResponseEntity<Cita> buscar(@PathVariable Long id){
        try{
            Cita cita = citaService.findById(id);
            return  ResponseEntity.ok(cita);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation (summary = "actualizar citas", description = "actualiza una lista de las citas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "cita actualizada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cita.class))),
            @ApiResponse(responseCode = "404", description = "cita no encontrada")
    })
    public ResponseEntity<Cita> actualizar(@PathVariable Long id,@RequestBody Cita cita ){
        try {
            Cita pacExistente = citaService.findById(id);

            pacExistente.setPacienteId(cita.getPacienteId());
            pacExistente.setRun(cita.getRun());
            pacExistente.setCorreo(cita.getCorreo());
            pacExistente.setFecha(cita.getFecha());

            citaService.save(pacExistente);
            return ResponseEntity.ok(pacExistente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation (summary = "Eliminar citas", description = "Elimina una cita de la lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "cita eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "cita no encontrada")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            citaService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
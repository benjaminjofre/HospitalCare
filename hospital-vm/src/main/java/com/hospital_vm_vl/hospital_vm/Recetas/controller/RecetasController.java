package com.hospital_vm_vl.hospital_vm.Recetas.controller;


import com.hospital_vm_vl.hospital_vm.Recetas.model.Recetas;
import com.hospital_vm_vl.hospital_vm.Recetas.service.RecetasService;
import com.hospital_vm_vl.hospital_vm.cita.model.Cita;
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
@RequestMapping("/api/v1/Recetas")
@Tag(name = "Recetas", description = "Operaciones realcionadas con las Recetas")
public class RecetasController {

    @Autowired
    private RecetasService recetasService;

    @GetMapping
    @Operation(summary = "Obtener Recetas", description = "obtiene una lista de las Recetas")
    public ResponseEntity<List<Recetas>> listar() {
        List<Recetas> recetas = recetasService.findAll();
        if (recetas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(recetas);
    }

    @PostMapping
    @Operation(summary = "insertar Recetas", description = "inserta una lista de las Recetas")
    public ResponseEntity<Recetas> guardar(@RequestBody Recetas recetas) {
        try {
            Recetas nuevoRecetas = recetasService.save(recetas);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRecetas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Recetas por id", description = "obtiene una lista de las Recetas")
    public ResponseEntity<Recetas> buscar(@PathVariable Long id) {
        try {
            Recetas recetas = recetasService.findById(id);
            return ResponseEntity.ok(recetas);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation (summary = "actualizar Recetas", description = "actualiza una lista de las Recetas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receta actualizado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cita.class))),
            @ApiResponse(responseCode = "404", description = "Receta no encontrado")
    })
    public ResponseEntity<Recetas> actualizar(@PathVariable Long id, @RequestBody Recetas recetas) {
        try {
            Recetas pacExistente = recetasService.findById(id);

            pacExistente.setNombreRemedio(recetas.getNombreRemedio());
            pacExistente.setNombresMedico(recetas.getNombresMedico());
            pacExistente.setApellidoMedico(recetas.getApellidoMedico());
            pacExistente.setPrescripciones(recetas.getPrescripciones());

            recetasService.save(pacExistente);
            return ResponseEntity.ok(pacExistente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Recetas", description = "Elimina un Receta de la lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receta eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Receta no encontrado")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            recetasService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

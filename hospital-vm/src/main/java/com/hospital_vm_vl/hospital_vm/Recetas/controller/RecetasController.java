package com.hospital_vm_vl.hospital_vm.Recetas.controller;


import com.hospital_vm_vl.hospital_vm.Recetas.model.Recetas;
import com.hospital_vm_vl.hospital_vm.Recetas.service.RecetasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Recetas")
public class RecetasController {

    @Autowired
    private RecetasService recetasService;

    @GetMapping
    public ResponseEntity<List<Recetas>> listar() {
        List<Recetas> recetas = recetasService.findAll();
        if (recetas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(recetas);
    }

    @PostMapping
    public ResponseEntity<Recetas> guardar(@RequestBody Recetas recetas) {
        try {
            Recetas nuevoRecetas = recetasService.save(recetas);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRecetas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recetas> buscar(@PathVariable Long id) {
        try {
            Recetas recetas = recetasService.findById(id);
            return ResponseEntity.ok(recetas);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            recetasService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

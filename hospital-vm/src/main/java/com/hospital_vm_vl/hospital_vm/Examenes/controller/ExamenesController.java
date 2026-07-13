package com.hospital_vm_vl.hospital_vm.Examenes.controller;


import com.hospital_vm_vl.hospital_vm.Examenes.model.Examenes;
import com.hospital_vm_vl.hospital_vm.Examenes.service.ExamenesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Examenes")
public class ExamenesController {

    @Autowired
    private ExamenesService examenesService;

    @GetMapping
    public ResponseEntity<List<Examenes>> listar() {
        List<Examenes> examenes = examenesService.findAll();
        if (examenes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(examenes);
    }

    @PostMapping
    public ResponseEntity<Examenes> guardar(@RequestBody Examenes examenes) {
        try {
            Examenes nuevoPaciente = examenesService.save(examenes);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPaciente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Examenes> buscar(@PathVariable Long id) {
        try {
            Examenes paciente = examenesService.findById(id);
            return ResponseEntity.ok(paciente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            examenesService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

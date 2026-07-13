package com.hospital_vm_vl.hospital_vm.Urgencias.controller;

import com.hospital_vm_vl.hospital_vm.Urgencias.model.Urgencias;
import com.hospital_vm_vl.hospital_vm.Urgencias.service.UrgenciasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/Urgencias")
public class UrgenciasController {

    @Autowired
    private UrgenciasService urgenciasService;

    @GetMapping
    public ResponseEntity<List<Urgencias>> listar() {
        List<Urgencias> urgencias = urgenciasService.findAll();
        if (urgencias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(urgencias);
    }

    @PostMapping
    public ResponseEntity<Urgencias> guardar(@RequestBody Urgencias urgencias) {
        try {
            Urgencias nuevoUrgencias = urgenciasService.save(urgencias);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUrgencias);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Urgencias> buscar(@PathVariable Long id) {
        try {
            Urgencias urgencias = urgenciasService.findById(id);
            return ResponseEntity.ok(urgencias);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            urgenciasService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

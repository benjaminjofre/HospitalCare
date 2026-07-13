package com.hospital_vm_vl.hospital_vm.Especialidades.controller;


import com.hospital_vm_vl.hospital_vm.Especialidades.model.Especialidades;
import com.hospital_vm_vl.hospital_vm.Especialidades.service.EspecialidadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Especialidades")
public class EspecialidadesController {

    @Autowired
    private EspecialidadesService especialidadesService;

    @GetMapping
    public ResponseEntity<List<Especialidades>> listar() {
        List<Especialidades> especialidades = especialidadesService.findAll();
        if (especialidades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(especialidades);
    }

    @PostMapping
    public ResponseEntity<Especialidades> guardar(@RequestBody Especialidades especialidades) {
        try {
            Especialidades nuevoEspecialidades = especialidadesService.save(especialidades);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEspecialidades);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidades> buscar(@PathVariable Long id) {
        try {
            Especialidades especialidades = especialidadesService.findById(id);
            return ResponseEntity.ok(especialidades);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            especialidadesService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

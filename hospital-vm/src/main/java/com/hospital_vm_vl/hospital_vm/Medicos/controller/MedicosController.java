package com.hospital_vm_vl.hospital_vm.Medicos.controller;

import com.hospital_vm_vl.hospital_vm.Medicos.model.Medicos;
import com.hospital_vm_vl.hospital_vm.Medicos.repository.MedicosRepository;
import com.hospital_vm_vl.hospital_vm.Medicos.service.MedicosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/Medicos")
public class MedicosController {

    @Autowired
    private MedicosService medicosService;

    @GetMapping
    public ResponseEntity<List<Medicos>> listar() {
        List<Medicos> medicos = medicosService.findAll();
        if (medicos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medicos);
    }


    @PostMapping
    public ResponseEntity<Medicos> guardar(@RequestBody Medicos medicos) {
        try {
            Medicos nuevoMedicos = medicosService.save(medicos);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMedicos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicos> buscar(@PathVariable Long id) {
        try {
            Medicos medicos = medicosService.findById(id);
            return ResponseEntity.ok(medicos);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            medicosService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

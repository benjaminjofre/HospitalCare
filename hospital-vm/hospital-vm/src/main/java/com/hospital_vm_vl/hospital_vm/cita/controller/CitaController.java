package com.hospital_vm_vl.hospital_vm.cita.controller;

import com.hospital_vm_vl.hospital_vm.cita.model.Cita;
import com.hospital_vm_vl.hospital_vm.cita.service.CitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public ResponseEntity<List<Cita>> listar() {
        List<Cita> citas = citaService.findAll();
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }

    @PostMapping
    public ResponseEntity<Cita> guardar(@Valid @RequestBody Cita cita) {
        try {
            Cita nuevaCita = citaService.save(cita);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCita);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
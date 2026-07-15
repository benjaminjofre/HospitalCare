package com.hospital_vm_vl.hospital_vm.Cita.service;

import com.hospital_vm_vl.hospital_vm.Cita.dto.PacienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "paciente-client", url = "http://localhost:8080/api/Paciente")
public interface PacienteClient {

    @GetMapping("/{id}")
    PacienteDTO obtenerPacientePorId(@PathVariable("id") Long id);
}
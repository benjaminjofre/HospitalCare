package com.hospital_vm_vl.hospital_vm.cita.service;

import com.hospital_vm_vl.hospital_vm.cita.repository.CitaRepository;
import com.hospital_vm_vl.hospital_vm.cita.model.Cita;
import com.hospital_vm_vl.hospital_vm.cita.model.PacienteDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
@Transactional
public class CitaService {

    private static final Logger log = LoggerFactory.getLogger(CitaService.class);

    @Autowired
    private CitaRepository CitaRepository;

    @Autowired
    private PacienteClient pacienteClient;

    public List<Cita> findAll() { return CitaRepository.findAll(); }

    public Cita findById(long id) { return CitaRepository.findById(id).get(); }

    public Cita save(Cita cita) {
        log.info("Iniciando validación para agendar cita. Verificando paciente ID: {}", cita.getPacienteId());

        PacienteDTO paciente = pacienteClient.obtenerPacientePorId(cita.getPacienteId());

        if (paciente != null) {
            log.info("Paciente verificado con éxito. Guardando cita en la base de datos.");
            return CitaRepository.save(cita);
        } else {
            log.error("Fallo al crear cita: El paciente con ID {} no fue encontrado.", cita.getPacienteId());
            throw new RuntimeException("El paciente no existe en el sistema.");
        }
    }

    public void delete(long id) { CitaRepository.deleteById(id); }
}
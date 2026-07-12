package com.hospital_vm_vl.hospital_vm.paciente.repository;

import com.hospital_vm_vl.hospital_vm.paciente.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}

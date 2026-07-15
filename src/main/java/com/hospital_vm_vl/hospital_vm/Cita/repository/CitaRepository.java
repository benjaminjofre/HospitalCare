package com.hospital_vm_vl.hospital_vm.Cita.repository;

import com.hospital_vm_vl.hospital_vm.Cita.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CitaRepository extends JpaRepository<Cita, Long>{

}
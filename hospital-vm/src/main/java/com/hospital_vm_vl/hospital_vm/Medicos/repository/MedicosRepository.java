package com.hospital_vm_vl.hospital_vm.Medicos.repository;


import com.hospital_vm_vl.hospital_vm.Medicos.model.Medicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicosRepository extends JpaRepository <Medicos, Long> {
}

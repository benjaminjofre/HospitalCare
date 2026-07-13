package com.hospital_vm_vl.hospital_vm.Examenes.repository;

import com.hospital_vm_vl.hospital_vm.Examenes.model.Examenes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamenesRepository extends JpaRepository<Examenes, Long> {
}

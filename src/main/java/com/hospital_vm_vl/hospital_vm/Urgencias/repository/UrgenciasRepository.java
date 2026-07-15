package com.hospital_vm_vl.hospital_vm.Urgencias.repository;

import com.hospital_vm_vl.hospital_vm.Urgencias.model.Urgencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrgenciasRepository extends JpaRepository<Urgencias, Long> {
}

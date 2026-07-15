package com.hospital_vm_vl.hospital_vm.Especialidades.repository;

import com.hospital_vm_vl.hospital_vm.Especialidades.model.Especialidades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EspecialidadesRepository extends JpaRepository<Especialidades, Long> {
}

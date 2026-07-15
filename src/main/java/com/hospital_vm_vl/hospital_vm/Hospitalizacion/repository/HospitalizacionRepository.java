package com.hospital_vm_vl.hospital_vm.Hospitalizacion.repository;

import com.hospital_vm_vl.hospital_vm.Hospitalizacion.model.Hospitalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalizacionRepository extends JpaRepository<Hospitalizacion, Long> {
}

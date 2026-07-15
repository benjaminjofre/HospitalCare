package com.hospital_vm_vl.hospital_vm.FichasClinicas.repository;


import com.hospital_vm_vl.hospital_vm.FichasClinicas.model.FichasClinicas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichasClinicasRepository extends JpaRepository<FichasClinicas, Long> {
}

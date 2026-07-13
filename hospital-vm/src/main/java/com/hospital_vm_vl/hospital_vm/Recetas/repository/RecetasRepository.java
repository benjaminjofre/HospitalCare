package com.hospital_vm_vl.hospital_vm.Recetas.repository;


import com.hospital_vm_vl.hospital_vm.Recetas.model.Recetas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetasRepository extends JpaRepository<Recetas, Long> {
}

package com.hospital_vm_vl.hospital_vm.Recetas.service;

import com.hospital_vm_vl.hospital_vm.Recetas.model.Recetas;
import com.hospital_vm_vl.hospital_vm.Recetas.repository.RecetasRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RecetasService {

    @Autowired
    private RecetasRepository recetasRepository;

    public List<Recetas> findAll() { return recetasRepository.findAll(); }

    public Recetas findById(long id) { return recetasRepository.findById(id).get(); }

    public Recetas save(Recetas recetas) {
        return recetasRepository.save(recetas);
    }

    public void delete(long id) {
        recetasRepository.deleteById(id);
    }
}

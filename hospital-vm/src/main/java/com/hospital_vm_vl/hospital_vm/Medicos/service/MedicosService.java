package com.hospital_vm_vl.hospital_vm.Medicos.service;

import com.hospital_vm_vl.hospital_vm.Medicos.model.Medicos;
import com.hospital_vm_vl.hospital_vm.Medicos.repository.MedicosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MedicosService {

    @Autowired
    private MedicosRepository medicosRepository;

    public List<Medicos> findAll() {
        return medicosRepository.findAll();
    }

    public Medicos findById(long id) { return medicosRepository.findById(id).get();}

    public Medicos save(Medicos medicos) { return medicosRepository.save(medicos);
    }

    public void delete(long id) {medicosRepository.deleteById(id);
    }
}

package com.hospital_vm_vl.hospital_vm.Examenes.service;

import com.hospital_vm_vl.hospital_vm.Examenes.model.Examenes;
import com.hospital_vm_vl.hospital_vm.Examenes.repository.ExamenesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class ExamenesService {

    @Autowired
    private ExamenesRepository examenesRepository;

    public List<Examenes> findAll() {
        return examenesRepository.findAll();
    }

    public Examenes findById(long id) {return examenesRepository.findById(id).get();
    }

    public Examenes save(Examenes examenes) {
        return examenesRepository.save(examenes);
    }

    public void delete(long id) {examenesRepository.deleteById(id);
    }
}

package com.hospital_vm_vl.hospital_vm.Urgencias.service;


import com.hospital_vm_vl.hospital_vm.Urgencias.model.Urgencias;
import com.hospital_vm_vl.hospital_vm.Urgencias.repository.UrgenciasRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class UrgenciasService {

    @Autowired
    private UrgenciasRepository urgenciasRepository;

    public List<Urgencias> findAll() {
        return urgenciasRepository.findAll();
    }

    public Urgencias findById(long id) {return urgenciasRepository.findById(id).get();
    }

    public Urgencias save(Urgencias urgencias) {
        return urgenciasRepository.save(urgencias);
    }

    public void delete(long id) {urgenciasRepository.deleteById(id);}
}

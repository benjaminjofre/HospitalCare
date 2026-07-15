package com.hospital_vm_vl.hospital_vm.FichasClinicas.service;


import com.hospital_vm_vl.hospital_vm.FichasClinicas.repository.FichasClinicasRepository;
import com.hospital_vm_vl.hospital_vm.FichasClinicas.model.FichasClinicas;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FichasClinicasService {

    @Autowired
    private FichasClinicasRepository fichasClinicasRepository;

    public List<FichasClinicas> findAll() {
        return fichasClinicasRepository.findAll();
    }

    public FichasClinicas findById(long id) {
        return fichasClinicasRepository.findById(id).get();
    }

    public FichasClinicas save(FichasClinicas fichasClinicas) {
        return fichasClinicasRepository.save(fichasClinicas);
    }

    public void delete(long id) {fichasClinicasRepository.deleteById(id);
    }
}

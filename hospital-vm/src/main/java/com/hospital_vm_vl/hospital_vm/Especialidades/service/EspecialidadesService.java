package com.hospital_vm_vl.hospital_vm.Especialidades.service;


import com.hospital_vm_vl.hospital_vm.Especialidades.model.Especialidades;
import com.hospital_vm_vl.hospital_vm.Especialidades.repository.EspecialidadesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EspecialidadesService {

    @Autowired
    private EspecialidadesRepository especialidadesRepository;

    public List<Especialidades> findAll() {
        return especialidadesRepository.findAll();
    }

    public Especialidades findById(long id) {return especialidadesRepository.findById(id).get();
    }

    public Especialidades save(Especialidades especialidades) {
        return especialidadesRepository.save(especialidades);
    }

    public void delete(long id) {especialidadesRepository.deleteById(id);}
}

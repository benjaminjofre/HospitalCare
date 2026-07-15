package com.hospital_vm_vl.hospital_vm.Hospitalizacion.service;

import com.hospital_vm_vl.hospital_vm.Hospitalizacion.model.Hospitalizacion;
import com.hospital_vm_vl.hospital_vm.Hospitalizacion.repository.HospitalizacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class HospitalizacionService {

    @Autowired
    private HospitalizacionRepository hospitalizacionRepository;

    public List<Hospitalizacion> findAll() {
        return hospitalizacionRepository.findAll();
    }

    public Hospitalizacion findById(long id) {return hospitalizacionRepository.findById(id).get();
    }

    public Hospitalizacion save(Hospitalizacion hospitalizacion) {
        return hospitalizacionRepository.save(hospitalizacion);
    }

    public void delete(long id) {hospitalizacionRepository.deleteById(id);
    }
}

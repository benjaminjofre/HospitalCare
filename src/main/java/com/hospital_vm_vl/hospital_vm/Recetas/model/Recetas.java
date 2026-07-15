package com.hospital_vm_vl.hospital_vm.Recetas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Recetas")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Recetas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombreRemedio;

    @Column(nullable = false)
    private String nombresMedico;

    @Column(nullable = false)
    private String ApellidoMedico;

    @Column(unique = false, length = 101, nullable = false)
    private String prescripciones;


}

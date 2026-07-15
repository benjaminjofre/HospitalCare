package com.hospital_vm_vl.hospital_vm.Especialidades.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="Especialidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Especialidades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El ID del Medico es obligatorio")
    @Positive(message = "El ID del Medico debe ser un número positivo")
    @Column(name = "medico_id", nullable = false)
    private Long MedicosId;

    @Column(nullable = false)
    private String nombre;
}

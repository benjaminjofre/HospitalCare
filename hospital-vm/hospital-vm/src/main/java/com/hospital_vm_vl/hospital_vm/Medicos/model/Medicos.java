package com.hospital_vm_vl.hospital_vm.Medicos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name="Cita")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El RUN es obligatorio")
    @Size(max = 13, message = "El RUN no puede superar los 13 caracteres")
    @Column(unique = true, length = 13, nullable = false)
    private String run;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe tener un formato de correo válido")
    @Column(nullable = false)
    private String correo;
}

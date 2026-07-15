package com.hospital_vm_vl.hospital_vm.Cita.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name="Cita")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El RUN es obligatorio")
    @Size(max = 13, message = "El RUN no puede superar los 13 caracteres")
    @Column(unique = true, length = 13, nullable = false)
    private String run;

    @NotNull(message = "La fecha es obligatoria")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe tener un formato de correo válido")
    @Column(nullable = false)
    private String correo;

    @NotNull(message = "El ID del paciente es obligatorio")
    @Positive(message = "El ID del paciente debe ser un número positivo")
    @Column(name = "paciente_id", nullable = false)
    private Long pacienteId;
}
package com.hospital_vm_vl.hospital_vm.Cita.exception;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private String mensaje;
    private String detalle;
    private int status;
    private LocalDateTime timestamp;
}

package com.hospital_vm_vl.hospital_vm.Cita.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> manejar(ResourceNotFoundException ex){

        ErrorResponse error = new ErrorResponse();

        error.setMensaje(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }
}

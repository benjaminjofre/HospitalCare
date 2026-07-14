package com.example.conexion_de_hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ConexionDeHospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConexionDeHospitalApplication.class, args);
	}

}

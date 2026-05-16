package com.tiendacarta.vendedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.tiendacarta.vendedor.Client")
public class VendedorApplication {
	public static void main(String[] args) {
		SpringApplication.run(VendedorApplication.class, args);
	}

}

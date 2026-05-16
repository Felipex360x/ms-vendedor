package com.tiendacarta.vendedor.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long UsuarioId;
    private Integer cantidadVendido;
    private String historialVendido;
    private Integer mayorVendido;
    private String detalleMayor;
    private Integer menorVendido;
    private String detalleMenor;
    
}
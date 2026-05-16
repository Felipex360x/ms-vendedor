package com.tiendacarta.vendedor.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class VendedorDTO {
    private Long id;
    /*parte de usuario */
    private Long UsuarioId;
    private String nombreUsuario;
    private String correoUsuario;
    private Integer cantidadVendido;
    private String historialVendido;
    private Integer mayorVendido;
    private String detalleMayor;
    private Integer menorVendido;
    private String detalleMenor;
}

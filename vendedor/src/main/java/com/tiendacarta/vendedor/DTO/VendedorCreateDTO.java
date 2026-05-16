package com.tiendacarta.vendedor.DTO;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendedorCreateDTO {

    @NotNull(message = "ingrea el id del usuario")
    private Long usuarioId;
    @NotNull(message = "ingrea la cantidad vendida")
    private Integer cantidadVendido;
    @NotNull(message = "ingrese el historial de vendidos")
    private String historialVendido;
    @NotNull(message = "ingrese el mayor valor vendido")
    private Integer mayorVendido;
    @NotNull(message = "ingrese el detalle del valor mas vendido")
    private String detalleMayor;
    @NotNull(message = "ingrese el menor valor vendido")
    private Integer menorVendido;
    @NotNull(message = "ingrese el detalle del valor menos vendido")
    private String detalleMenor;
}

package com.tiendacarta.vendedor.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tiendacarta.vendedor.DTO.UsuarioDTO;

@FeignClient(
    name ="usuarios",
    url = "${usuario.service.url}"

)
public interface UsuarioClient {
    @GetMapping("/api/v2/usuarios/{id}")
    UsuarioDTO obtenerUsuarios(@PathVariable("id")Long id);
    
}

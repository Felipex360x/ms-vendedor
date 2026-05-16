package com.tiendacarta.vendedor.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiendacarta.vendedor.DTO.VendedorCreateDTO;
import com.tiendacarta.vendedor.DTO.VendedorDTO;
import com.tiendacarta.vendedor.Service.VendedorService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/v2/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public ResponseEntity<List<VendedorDTO>> getAll(){
        return ResponseEntity.ok(vendedorService.findall());
    }
    @GetMapping("/{id}")
    public ResponseEntity<VendedorDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(vendedorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<VendedorDTO> crear(@Valid @RequestBody VendedorCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vendedorService.crear(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        vendedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendedorDTO> actualizar(@PathVariable Long id, @RequestBody VendedorCreateDTO dto){
        VendedorDTO vendedorActualizado = vendedorService.actualizar(id, dto);
        return new ResponseEntity<>(vendedorActualizado,HttpStatus.OK);

    }

    
}

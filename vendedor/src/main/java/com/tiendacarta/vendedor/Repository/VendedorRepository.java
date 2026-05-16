package com.tiendacarta.vendedor.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendacarta.vendedor.Model.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor,Long> {
    
}

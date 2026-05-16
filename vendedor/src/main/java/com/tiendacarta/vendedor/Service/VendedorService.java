package com.tiendacarta.vendedor.Service;


import feign.FeignException;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tiendacarta.vendedor.Client.UsuarioClient;
import com.tiendacarta.vendedor.DTO.UsuarioDTO;
import com.tiendacarta.vendedor.DTO.VendedorCreateDTO;
import com.tiendacarta.vendedor.DTO.VendedorDTO;
import com.tiendacarta.vendedor.Excepction.RecursoNoEncontradoException;
import com.tiendacarta.vendedor.Excepction.ServicioNoDisponibleException;
import com.tiendacarta.vendedor.Model.Vendedor;
import com.tiendacarta.vendedor.Repository.VendedorRepository;

import org.springframework.stereotype.Service;


@Service
public class VendedorService {

    private static final Logger Log = LoggerFactory.getLogger(VendedorService.class);

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    public List<VendedorDTO> findall(){
        Log.info("consultando todos los vendedores");
        return vendedorRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public VendedorDTO findById(Long id){
        Vendedor vendedor = vendedorRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Vendedor no Encotrado"+ id));
        return toDTO(vendedor);
    }

    public VendedorDTO crear(VendedorCreateDTO dto){
        Log.info("creando Vendedor: usuarioId={}",dto.getUsuarioId());
        UsuarioDTO usuario;
        try{
            Log.info("Consultando a Servicio de usuario para el usuarioId={}",dto.getUsuarioId());
            usuario = usuarioClient.obtenerUsuarios(dto.getUsuarioId());
            Log.info("Usuario encotrado: nombre={},correo={}",usuario.getNombre(),usuario.getCorreo());
        } catch (FeignException.NotFound e){
            throw new RecursoNoEncontradoException("Usuario no encotrado"+ dto.getUsuarioId());
        } catch(FeignException e){
            Log.error("Error al cactactar con servico de usuari: {}",e.getMessage());
            throw new ServicioNoDisponibleException("servicio de usuari no dispobibl,intnte nuevamente ");
        }

        Vendedor vendedor = new Vendedor();
        vendedor.setUsuarioId(dto.getUsuarioId());
        vendedor.setCantidadVendido(dto.getCantidadVendido());
        vendedor.setHistorialVendido(dto.getHistorialVendido());
        vendedor.setMayorVendido(dto.getMayorVendido());
        vendedor.setDetalleMayor(dto.getDetalleMayor());
        vendedor.setMenorVendido(dto.getMenorVendido());
        vendedor.setDetalleMenor(dto.getDetalleMenor());
        

        Vendedor guardar= vendedorRepository.save(vendedor);
        Log.info("Vendedor Creado id={} - usuario={} - correo={}",guardar.getId(),usuario.getNombre(),usuario.getCorreo());
        return toDTOConNombre(guardar,usuario.getNombre(),usuario.getCorreo());
    }

    public VendedorDTO eliminar(Long id){
        Log.info("eliminando Vendedor por Id={}",id);
        Vendedor vendedor = vendedorRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("vendedor no ecntrado"+ id));
        return toDTO(vendedorRepository.save(vendedor));
    }

    private VendedorDTO toDTO(Vendedor v){
        return new VendedorDTO(
        v.getId(),
        v.getUsuarioId(),
        null,
        null,
        v.getCantidadVendido(),
        v.getHistorialVendido(),
        v.getMayorVendido(),
        v.getDetalleMayor(),
        v.getMenorVendido(),
        v.getDetalleMenor()
        );
    }

    public VendedorDTO actualizar(Long id, VendedorCreateDTO dto){
        Log.info("se actualizo el Vendedor id={}",id);
        Vendedor vendedor = vendedorRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("Vendedor no Encontrado" + id));
        UsuarioDTO usuario;
        try{
            usuario = usuarioClient.obtenerUsuarios(dto.getUsuarioId());
        } catch(FeignException.NotFound e){
            throw new RecursoNoEncontradoException("el usuario no existe:" +dto.getUsuarioId());
        } catch(FeignException e){
            throw new ServicioNoDisponibleException("servicio de usuario no se esncuetra disponible");
        }

        vendedor.setUsuarioId(dto.getUsuarioId());
        vendedor.setCantidadVendido(dto.getCantidadVendido());
        vendedor.setHistorialVendido(dto.getHistorialVendido());
        vendedor.setMayorVendido(dto.getMayorVendido());
        vendedor.setDetalleMayor(dto.getDetalleMayor());
        vendedor.setMenorVendido(dto.getMenorVendido());
        vendedor.setDetalleMenor(dto.getDetalleMenor());
        Vendedor actualizado = vendedorRepository.save(vendedor);
        Log.info("Comprador Actualizado id={} - usuario={}",actualizado.getId());
        return toDTOConNombre(actualizado,usuario.getNombre(),usuario.getCorreo());
    }

    public VendedorDTO toDTOConNombre(Vendedor v, String nombreUsuario, String correoUsuario){
        return new VendedorDTO(
            v.getId(),
            v.getUsuarioId(),
            nombreUsuario,
            correoUsuario,
            v.getCantidadVendido(),
            v.getHistorialVendido(),
            v.getMayorVendido(),
            v.getDetalleMayor(),
            v.getMenorVendido(),
            v.getDetalleMenor()
        );
    }



}

package gm.inventarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import gm.inventarios.modelo.producto;

public interface ProductoRepositorio extends JpaRepository<producto, Integer>{
    
}

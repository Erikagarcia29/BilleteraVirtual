package ar.com.ada.api.billetera.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.ada.api.billetera.entities.Usuario;

public interface PersonaRepository extends JpaRepository<Usuario, Integer> {
    
}
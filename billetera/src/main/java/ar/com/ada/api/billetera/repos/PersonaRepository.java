package ar.com.ada.api.billetera.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.billetera.entities.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    
}
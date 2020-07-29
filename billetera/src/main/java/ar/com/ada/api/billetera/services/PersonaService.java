package ar.com.ada.api.billetera.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.billetera.entities.Persona;
import ar.com.ada.api.billetera.repos.PersonaRepository;

@Service
public class PersonaService {
    @Autowired
    PersonaRepository repo;

    public void grabar(Persona persona) {
        repo.save(persona);
    }
}
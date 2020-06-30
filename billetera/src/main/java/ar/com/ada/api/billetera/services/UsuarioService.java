package ar.com.ada.api.billetera.services;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.billetera.entities.Billetera;
import ar.com.ada.api.billetera.entities.Cuenta;
import ar.com.ada.api.billetera.entities.Persona;
import ar.com.ada.api.billetera.entities.Usuario;
import ar.com.ada.api.billetera.repos.UsuarioRepository;
import ar.com.ada.api.billetera.security.Crypto;

@Service

public class UsuarioService {
     /*1.Metodo: Crear Usuario
    1.1-->Crear una Persona(setearle un usuario)
    1.2-->crear un usuario
    1.3-->Crear una billetera(setearle una persona)
    1.4-->Crear una cuenta en pesos y otra en dolares*/

    /* 2. Metodo: Iniciar Sesion 
    2.1-- recibe el username y la password
    2.2-- vamos a validar los datos
    2.3-- devolver un verdadero o falso
    */

    @Autowired
    UsuarioRepository repo;

    @Autowired
    PersonaService personaService;

    @Autowired
    BilleteraService billeteraService;

    public Usuario buscarPorUsername(String username) {
        return null;
    }

    public void login(String username, String password) {
    }


    public Usuario crearUsuario(String nombre, int pais, int tipoDocumento, String documento, Date fechaNacimiento,
            String email, String password) {

        /*
         * 1.1-->Crear una Persona(setearle un usuario) 1.2-->crear un usuario
         * 1.3-->Crear una billetera(setearle una persona) 1.4-->Crear una cuenta en
         * pesos y otra en dolares
         */
        Persona persona = new Persona();
        persona.setNombre(nombre);
        persona.setPaisId(pais);
        persona.setTipoDocumento(tipoDocumento);
        persona.setDocumento(documento);
        persona.setFechaNacimiento(fechaNacimiento);

        Usuario usuario = new Usuario();
        usuario.setUsername(email);
        usuario.setEmail(email);
        usuario.setPassword(Crypto.encrypt(password,email));

        persona.setUsuario(usuario);

        personaService.grabar(persona);


        Billetera billetera = new Billetera();

        Cuenta pesos = new Cuenta();

        pesos.setSaldo(new BigDecimal(0));
        pesos.setMoneda("ARS");

        Cuenta dolares = new Cuenta();

        pesos.setSaldo(new BigDecimal(0));
        pesos.setMoneda("USD");

        billetera.agregarCuenta(pesos);
        billetera.agregarCuenta(dolares);

        persona.setBilletera(billetera);

        billeteraService.grabar(billetera);


        billeteraService.cargarSaldo(new BigDecimal(500), "ARS", billetera.getBilleteraId(), "regalo", "Bienvenida por creacion de usuario");
        return usuario;
    }





}

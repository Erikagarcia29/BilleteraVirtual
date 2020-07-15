package ar.com.ada.api.billetera.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import ar.com.ada.api.billetera.entities.*;
import ar.com.ada.api.billetera.repos.UsuarioRepository;
import ar.com.ada.api.billetera.security.Crypto;
import ar.com.ada.api.billetera.sistema.comm.EmailService;

@Service

public class UsuarioService {
    /*
     * 1.Metodo: Crear Usuario 1.1-->Crear una Persona(setearle un usuario)
     * 1.2-->crear un usuario 1.3-->Crear una billetera(setearle una persona)
     * 1.4-->Crear una cuenta en pesos y otra en dolares
     */

    /*
     * 2. Metodo: Iniciar Sesion 2.1-- recibe el username y la password 2.2-- vamos
     * a validar los datos 2.3-- devolver un verdadero o falso
     */

    @Autowired
    UsuarioRepository repo;
    @Autowired
  UsuarioRepository usuarioRepository;

    @Autowired
    PersonaService personaService;

    @Autowired
    BilleteraService billeteraService;
    @Autowired 
    EmailService emailService;

    public Usuario buscarPorUsername(String username) {
        return repo.findByUsername(username);
    }

    public void login(String userName, String password) {
    

        /**
         * Metodo IniciarSesion recibe usuario y contraseña validar usuario y contraseña
         */

        Usuario u = buscarPorUsername(userName);

        if(u==null||!u.getPassword().equals(Crypto.encrypt(password,u.getUsername()))){

         throw new BadCredentialsException("Usuario o contraseña invalida");
           }
    }
    public Usuario crearUsuario (String nombre ,Integer pais, Integer tipoDocumento, String documento, Date fechaNacimiento,
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

        dolares.setSaldo(new BigDecimal(0));
        dolares.setMoneda("USD");

        billetera.agregarCuenta(pesos);
        billetera.agregarCuenta(dolares);

        persona.setBilletera(billetera);

        billeteraService.grabar(billetera);


        billeteraService.cargarSaldo(new BigDecimal(500), "ARS",
         billetera, "regalo", "Bienvenida por creacion de usuario");

         emailService.SendEmail(email, "Bienvenido a La Billetera Virtual de ADA","¡¡FELICITACIONES!! Te regalamos 500 pesos para iniar al usar tu billetera");
        return usuario;

    }
    public Usuario buscarPorEmail(String email){
      
     
        return repo.findByEmail(email);
    }
    
    
    public Usuario buscarPor(Integer id) {
        Optional<Usuario> usuarioOp = usuarioRepository.findById(id);
        if (usuarioOp.isPresent()) {
            return usuarioOp.get();
          }
          return null;
        }
      
}
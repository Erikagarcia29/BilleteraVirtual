package ar.com.ada.api.billetera.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.billetera.entities.Usuario;
import ar.com.ada.api.billetera.models.request.LoginRequest;
import ar.com.ada.api.billetera.models.request.RegistrationRequest;
import ar.com.ada.api.billetera.models.response.JwtResponse;
import ar.com.ada.api.billetera.models.response.RegistrationResponse;
import ar.com.ada.api.billetera.security.jwt.JWTTokenUtil;
import ar.com.ada.api.billetera.services.*;



//AuthController
@RestController
public class AuthController {

    @Autowired
    UsuarioService usuarioService;
    /*
    *@Autowired private AuthenticationManager authenticationManager;
    */
    @Autowired
    private JWTTokenUtil  jwtTokenUtil;

    @Autowired
    private JWTUserDetailsService userDetailsServices; 

        //Auth: autentication -> autenticacion 
    @PostMapping ("auth/register")
    public ResponseEntity<RegistrationResponse> postRegisterUser(@RequestBody RegistrationRequest req){
        RegistrationResponse r = new RegistrationResponse();
        // aca creamos la persona y el usuario a travez del service.
        // insertar codigo aqui 
        
        Usuario usuario = usuarioService.crearUsuario(req.fullName, req.country, req.identificationType, req.identification, req.birthDate, req.email, req.password);

        r.isOk = true;
        r.message = "te registraste con exito";
        r.userId = usuario.getUsuarioId() ;//<-- aqui ponemos el numerito de la id para darle al front 
        return ResponseEntity.ok(r);
    }

    

    @PostMapping("auth/login")//probando nuestro login
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest)throws Exception{
        
        usuarioService.login(authenticationRequest.username, authenticationRequest.password);
        
        UserDetails userDetails = userDetailsServices.loadUserByUsername(authenticationRequest.username);

        String token =jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
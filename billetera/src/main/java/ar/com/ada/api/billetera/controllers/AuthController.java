package ar.com.ada.api.billetera.controllers;

import java.util.Map;

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
import ar.com.ada.api.billetera.models.response.LoginResponse;
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
        
        Usuario usuario = usuarioService.crearUsuario(req.fullName, req.country, req.identificationType,
         req.identification, req.birthDate, req.email, req.password);

        r.isOk = true;
        r.message = "te registraste con exito";
        r.userId = usuario.getUsuarioId() ;//<-- aqui ponemos el numerito de la id para darle al front 
        return ResponseEntity.ok(r);
    }

    

    @PostMapping("auth/login")//probando nuestro login
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest
        )throws Exception{
        
            Usuario usuarioLogueado = usuarioService.login(authenticationRequest.username, authenticationRequest.password);
        
            UserDetails userDetails = usuarioService.getUserAsUserDetail(usuarioLogueado);
            Map<String,Object> claims = usuarioService.getUserClaims(usuarioLogueado);
            
        //Genero los roles pero con los Claims(los propositos)
        //En este caso nuestros claims tienen info de la billetera(billetera id)
        //Esta info va a viajar con el token, o sea, cualquiera puede
        //ver las billeterasid de que user pertenecen si logran interceptar el token
        //Por eso es que en cada request debemos validar el token(firma)
        String token = jwtTokenUtil.generateToken(userDetails, claims);
         //Cambio para que devuelva el full perfil
         Usuario u = usuarioService.buscarPorUsername(authenticationRequest.username);

         LoginResponse r = new LoginResponse();
         r.id = u.getUsuarioId();
         r.billeteraId = u.getPersona().getBilletera().getBilleteraId();
         r.username = authenticationRequest.username;
         r.email = u.getEmail();
         r.token = token;
 
         return ResponseEntity.ok(r);
         //return ResponseEntity.ok(new JwtResponse(token));
 
    }
}
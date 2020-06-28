package ar.com.ada.api.billetera.models.request;

import java.sql.Date;

//registrationRequest
public class RegistrationRequest {
    public String fullName;//nombre persona
    public int country;// pais del usuario
    public int identificationType;//tipo de documento
    public String identification;//nro documento
    public Date birthDate;//fecha de nacimiento
    public String email;//email
    public String password;//contraseña elegida por el usuario

}
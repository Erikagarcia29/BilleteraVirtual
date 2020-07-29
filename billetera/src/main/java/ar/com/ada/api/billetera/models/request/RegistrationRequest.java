package ar.com.ada.api.billetera.models.request;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

//registrationRequest
public class RegistrationRequest {
    @NotBlank(message = "el nombre no puede ser nulo")
    public String fullName;// nombre persona
    @Min(1)
    public int country;// pais del usuario
    public int identificationType;// tipo de documento
    public String identification;// nro documento
    public Date birthDate;// fecha de nacimiento
    public String email;// email
    @NotBlank(message = "La contraseña debe tener al menos 8 digitos")
    public String password;// contraseña elegida por el usuario

}
package ar.com.ada.api.billetera.models.request;

import javax.validation.constraints.NotBlank;

//login request
public class LoginRequest {
    @NotBlank(message = "username es obligatorio")
    public String username;
    @NotBlank(message = "password es obligatorio")
    public String password;
}
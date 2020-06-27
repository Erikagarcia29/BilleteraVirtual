package ar.com.ada.api.billetera.entities;

import java.sql.Date;

public class Usuario {
    private Integer usuarioId ;
    private String username;
    private String passwors;
    private String email;
    private Date fechaLogin;
    private Persona persona;

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswors() {
        return passwors;
    }

    public void setPasswors(String passwors) {
        this.passwors = passwors;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaLogin() {
        return fechaLogin;
    }

    public void setFechaLogin(Date fechaLogin) {
        this.fechaLogin = fechaLogin;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
} 
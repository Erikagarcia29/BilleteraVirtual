package ar.com.ada.api.billetera.entities;

import java.util.*;

public class Billetera {

    private Integer billetera;
    private Persona persona;
    private List<Cuenta>cuenta = new ArrayList<>();

    public Integer getBilletera() {
        return billetera;
    }

    public void setBilletera(Integer billetera) {
        this.billetera = billetera;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Cuenta> getCuenta() {
        return cuenta;
    }

    public void setCuenta(List<Cuenta> cuenta) {
        this.cuenta = cuenta;
    }
    
}
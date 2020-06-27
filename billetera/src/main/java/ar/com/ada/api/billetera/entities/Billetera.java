package ar.com.ada.api.billetera.entities;

import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Billetera {
    @Id
    @Column(name = "billetera_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer billeteraId;
    @OneToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "persona_id")
    private Persona persona;

    @OneToMany(mappedBy = "billetera", cascade = CascadeType.ALL)
    private List<Cuenta> cuenta = new ArrayList<>();

    public Integer getBilleteraId() {
        return billeteraId;
    }

    public void setBilleteraId(Integer billeteraId) {
        this.billeteraId = billeteraId;
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
package ar.com.ada.api.billetera.entities;

import java.math.BigDecimal;
import java.sql.Date;

public class Transaccion {
    private Integer transaccionId;
    private Cuenta cuenta ;
    private Date fecha;
    private Integer estadoId;
    private BigDecimal importe;
    private String moneda ;
    private Integer tipoOperacion;
    private String conceptoOpearcion;
    private String detalle;
    private Integer deUsuarioId;
    private Integer aUsuarioId;

    public Integer getTransaccionId() {
        return transaccionId;
    }

    public void setTransaccionId(Integer transaccionId) {
        this.transaccionId = transaccionId;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Integer getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(Integer tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getConceptoOpearcion() {
        return conceptoOpearcion;
    }

    public void setConceptoOpearcion(String conceptoOpearcion) {
        this.conceptoOpearcion = conceptoOpearcion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Integer getDeUsuarioId() {
        return deUsuarioId;
    }

    public void setDeUsuarioId(Integer deUsuarioId) {
        this.deUsuarioId = deUsuarioId;
    }

    public Integer getaUsuarioId() {
        return aUsuarioId;
    }

    public void setaUsuarioId(Integer aUsuarioId) {
        this.aUsuarioId = aUsuarioId;
    }

} 
package ar.com.ada.api.billetera.models.request;

import java.math.BigDecimal;

public class EnvioSaldoRequest {
    public BigDecimal importe;
    public String moneda;
    public String email;
    public String motivo;
    public String detalle;
    
}
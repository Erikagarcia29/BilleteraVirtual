
package ar.com.ada.api.billetera.models.response;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.ada.api.billetera.entities.Transaccion.TipoTransaccionEnum;

public class MovimientosResponse {
    public Integer numeroDeTransaccion;
    public Date fecha;
    public BigDecimal importe;
    public String moneda;
    public TipoTransaccionEnum tipoOperacion;
    public String conceptoOperacion;
    public String detalle;
    public String aUsuario;
}
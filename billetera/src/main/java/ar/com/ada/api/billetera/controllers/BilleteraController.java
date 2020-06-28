package ar.com.ada.api.billetera.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class BilleteraController {
    /*
    *webMetodo 1:
    consultar saldo:GET
    ruta: /billeteras/{id}/saldos

    webMetodo 2 :
        cargar salgo:POST
        URL:/billeteras/{id}/recargas
        requestBody:
       { "moneda":
        "importe":}
        
     webMetodo 3:
        enviar saldo:post
        url:/billetera/{id}/envios
        requestBody:
        {
            "moneda":
            "importe":
            "email":
            "motivo":
            "detalleMotivo":
        }

     */
    
}
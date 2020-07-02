package ar.com.ada.api.billetera;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.ada.api.billetera.entities.Usuario;
import ar.com.ada.api.billetera.security.Crypto;
import ar.com.ada.api.billetera.services.BilleteraService;
import ar.com.ada.api.billetera.services.UsuarioService;



@SpringBootTest
class DemoApplicationTests {
	
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	BilleteraService billeteraService;

	
	
	@Test
	void contextLoads() {
		String textoClaro = "Este es un texto que todos pueden leer";

		// aca va algo que sepamos que cambie por cada usuario o transaccion
		String unSaltoLoco = "un numero random";

		// Aca vamos a dejar el texto encriptado(reversible!)
		String textoEncriptado = "";

		textoEncriptado = Crypto.encrypt(textoClaro, unSaltoLoco);

		//Este print no lo hagan en los testing reales! si bien sirve para buscar, lo mejor es
		//tenerlos desactivados! En tal caso debuguean!
		System.out.println("el texto encriptado es: "+textoEncriptado);

		// Aca vamos a dejar el texto desencriptado de "textoEncryptado"
		String textoDesencriptado = "";

		// Desencripto!!
		textoDesencriptado = Crypto.decrypt(textoEncriptado, unSaltoLoco);


		// Todo va a estar bien, si el "textoClaro" es igual al "textoDesencriptado";
		assertTrue(textoClaro.equals(textoDesencriptado));

	}


	@Test
	void HashTest() {

		String textoClaro = "Este es un texto que todos pueden leer";

		// aca va algo que sepamos que cambie por cada usuario o transaccion
		String unSaltoLoco = "algo atado al usuario, ej UserId 20";

		// Aca vamos a dejar el texto hasheado(NO reversible)
		String textoHasheado = "";

		textoHasheado = Crypto.hash(textoClaro, unSaltoLoco);


		//Este print no lo hagan en los testing reales! si bien sirve para buscar, lo mejor es
		//tenerlos desactivados! En tal caso debuguean!
		System.out.println("el texto hasheado es: "+textoHasheado);


		// Aca vamos a dejar el texto desencriptado de "textoEncryptado"
		String hashEsperado = "lxT/9Zj6PUyV/xTfCS90qfLMNEL7wnvg8VxsG/slFvZghZvQvFCZQvg584s6TMlkHqJ3wMA2J9rofsERmKGSUg==";

		// Todo va a estar bien, si el hash del texto es el 
		assertTrue(textoHasheado.equals(hashEsperado));

	}	

	@Test
    void CrearUsuarioTest() {

       
		
		Usuario usuario = usuarioService.crearUsuario("Karen", 32, 5, "21231123", new Date(), "karen@gmail.com",
				"a12345");

       // System.out.println("SALDO de usuario: " + usuario.getPersona().getBilletera().getCuenta("ARS").getSaldo());
	   
	 //Usuario usuarioVerificado = usuarioService.buscarPorUsername(usuario.getUsername());
	 
	 //assertTrue(usuario.getUsuarioId() == usuarioVerificado.getUsuarioId());
	   assertTrue(usuario.getUsuarioId()==1);
        assertTrue(usuario.getPersona().getBilletera().getCuenta("ARS").getSaldo().equals(new BigDecimal(500)));

	}
	

	@Test
	void EnviarSaldoTest() {

		Usuario usuarioEmisor = usuarioService.crearUsuario("Karen Envia", 32, 5 , "21231123", new Date(), "karenenvia@gmail.com", "a12345");
		Usuario usuarioReceptor = usuarioService.crearUsuario("Claudia Recibe", 32, 5 , "21231123", new Date(), "claudiarecibe@gmail.com", "a12345");

		Integer borigen = usuarioEmisor.getPersona().getBilletera().getBilleteraId();
		Integer bdestino = usuarioReceptor.getPersona().getBilletera().getBilleteraId();

		BigDecimal saldoOrigen = usuarioEmisor.getPersona().getBilletera().getCuenta("ARS").getSaldo();
		BigDecimal saldoDestino = usuarioReceptor.getPersona().getBilletera().getCuenta("ARS").getSaldo();

		billeteraService.enviarSaldo(new BigDecimal(1200), "ARS", borigen, bdestino, "PRESTAMO", "ya no me debes nada");


		BigDecimal saldoOrigenActualizado = billeteraService.consultarSaldo(borigen, "ARS");
		BigDecimal saldoDestinoActualizado = billeteraService.consultarSaldo(bdestino, "ARS");

		//AFIRMAMOS QUE, el saldo origen - 1200, sea igual al saldoOrigeActualizado
		//AFIRMAMOS QUE, el saldo destino + 1200, sea igual al saldoDestinoActualizado

		assertTrue(saldoOrigen.subtract(new BigDecimal(1200)).equals(saldoOrigenActualizado));
		assertTrue(saldoDestino.add(new BigDecimal(1200)).equals(saldoDestinoActualizado));


	}


}
	



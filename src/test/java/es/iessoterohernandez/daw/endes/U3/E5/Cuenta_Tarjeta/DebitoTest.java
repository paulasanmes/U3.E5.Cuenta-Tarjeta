package es.iessoterohernandez.daw.endes.U3.E5.Cuenta_Tarjeta;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DebitoTest {

	private Debito debito;
	private Cuenta cuentaAsociada;

	@BeforeEach
	public void setUp() {
		cuentaAsociada = new Cuenta("1234", "TitularCuenta");
		debito = new Debito("5678", "TitularTarjeta", new Date());
		debito.setCuenta(cuentaAsociada);
	}

	@AfterEach
	public void tearDown() {
		cuentaAsociada = null;
		debito = null;
	}

	@Test
	public void testRetirar() throws Exception {
		cuentaAsociada.ingresar(500.0);
		debito.retirar(200.0);
		assertEquals(300.0, cuentaAsociada.getSaldo(), 0.01);
	}

	@Test
	public void testIngresar() throws Exception {
		debito.ingresar(200.0);
		assertEquals(200.0, cuentaAsociada.getSaldo(), 0.01);
	}

	@Test
	public void testPagoEnEstablecimiento() throws Exception {
		cuentaAsociada.ingresar(300.0);
		debito.pagoEnEstablecimiento("Tienda", 150.0);
		assertEquals(150.0, cuentaAsociada.getSaldo());
	}

	@Test
	public void testGetSaldo() throws Exception {
		cuentaAsociada.ingresar(100.0);
		assertEquals(100.0, debito.getSaldo());
	}

	@Test
	public void testSetCuenta() {
		Cuenta nuevaCuenta = new Cuenta("9999", "NuevoTitular");
		debito.setCuenta(nuevaCuenta);
		assertEquals(nuevaCuenta.getSaldo(), debito.getSaldo());
	}
}

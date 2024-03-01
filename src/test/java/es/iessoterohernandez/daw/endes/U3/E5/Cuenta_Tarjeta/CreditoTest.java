package es.iessoterohernandez.daw.endes.U3.E5.Cuenta_Tarjeta;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreditoTest {

private Credito c;
private Cuenta cu;
private Tarjeta t;
    
    @BeforeEach
     void setUp() {
        Date fechaCaducidad = new Date();
        c = new Credito("123456789", "Raul", fechaCaducidad, 2000.0);
        cu = new Cuenta("987654321", "Juan Pérez");
       // t = new Tarjeta("987654321", "Lucas", fechaCaducidad);

    }

    @Test
     void testRetirar() throws Exception {
        c.retirar(0.0);
        assertEquals(1997.0, c.getCreditoDisponible());
    }

    @Test
     void testIngresar() throws Exception {
        c.ingresar(500.0);
        assertEquals(500.0, c.getSaldo());
    }

    @Test
     void testPagoEnEstablecimiento() throws Exception {
        c.pagoEnEstablecimiento("Tienda", 300.0);
        assertEquals(300.0, c.getSaldo());
    }

    @Test
     void testGetSaldo() {
        assertEquals(0.0, c.getSaldo());
    }

    @Test
     void testGetCreditoDisponible() {
        assertEquals(2000.0, c.getCreditoDisponible());
    }

    @Test
     void testLiquidar() throws Exception {
    	c.retirar(30.0);
    	c.pagoEnEstablecimiento("Tienda", 20.0);
    	
    	double saldoAnterior=c.getSaldo();
    	
    	c.liquidar(1, 2022);
    	
    	double saldoPosterior=c.getSaldo();
        
    }

}

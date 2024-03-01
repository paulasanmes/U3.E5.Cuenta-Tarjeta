package es.iessoterohernandez.daw.endes.U3.E5.Cuenta_Tarjeta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CuentaTest {

private Cuenta c;
    
    @BeforeEach
     void setUp() {
        c = new Cuenta("987654321", "Juan Pérez");
    }
    
    @Test
     void ingresarPositivoTest() throws Exception {
        c.ingresar(500.0);
        assertEquals(500.0, c.getSaldo());
    }
    
    @Test
     void ingresarNegativoTest() {
        Exception exception = assertThrows(Exception.class, () -> {
            c.ingresar(-200.0);
        });
        
        assertEquals("No se puede ingresar una cantidad negativa", exception.getMessage());
        assertEquals(0.0, c.getSaldo());
    }
    
    @Test
     void retirarPositivoTest() throws Exception {
        c.ingresar(1000.0);
        c.retirar(300.0);
        assertEquals(700.0, c.getSaldo());
    }
    
    @Test
     void retirarNegativoTest() {
        Exception exception = assertThrows(Exception.class, () -> {
            c.retirar(-150.0);
        });
        
        assertEquals("No se puede retirar una cantidad negativa", exception.getMessage());
        assertEquals(0.0, c.getSaldo());
    }
    
    @Test
     void retirarSaldoInsuficienteTest() {
        Exception exception = assertThrows(Exception.class, () -> {
            c.retirar(500.0);
        });
        
        assertEquals("Saldo insuficiente", exception.getMessage());
        assertEquals(0.0, c.getSaldo());
    }
    
    @Test
     void getSaldoSinMovimientosTest() {
        assertEquals(0.0, c.getSaldo());
    }
    
    @Test
     void getSaldoConMovimientosTest() throws Exception {
        c.ingresar(200.0);
        c.retirar(50.0);
        assertEquals(150.0, c.getSaldo());
    }
    
    @Test
     void ingresarConConceptoTest() throws Exception {
        c.ingresar("Depósito inicial", 1000.0);
        assertEquals(1000.0, c.getSaldo());
    }

    @Test
     void retirarConConceptoTest() throws Exception {
        c.ingresar(2000.0);
        c.retirar("Retiro en cajero", 500.0);
        assertEquals(1500.0, c.getSaldo());
    }

    @Test
     void addMovimientoTest() {
        Movimiento movimiento = new Movimiento();
        movimiento.setConcepto("Transferencia recibida");
        movimiento.setImporte(300.0);
        c.addMovimiento(movimiento);
        assertEquals(300.0, c.getSaldo());
    }

    @Test
     void secuenciaOperacionesTest() throws Exception {
        c.ingresar(3000.0);
        c.retirar(1000.0);
        c.ingresar(500.0);
        c.retirar(2000.0);
        assertEquals(500.0, c.getSaldo());
    }

   
    @Test
     void saldoCeroDespuesRetiroTest() throws Exception {
        c.ingresar(500.0);
        c.retirar(500.0);
        assertEquals(0.0, c.getSaldo());
    }

    @Test
     void movimientoNegativoTest() {
        Movimiento movimiento = new Movimiento();
        movimiento.setConcepto("Pago de deuda");
        movimiento.setImporte(-100.0);
        assertThrows(Exception.class, () -> {
            c.addMovimiento(movimiento);
        });
    }

}

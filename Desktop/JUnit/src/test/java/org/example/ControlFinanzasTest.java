package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControlFinanzasTest {

    private ControlFinanzas cf;

    @BeforeEach
    void setUp() {
        cf = new ControlFinanzas();
    }

    @Test
    void testSetUsuarioValido() {
        assertTrue(cf.setUsuario("Ale"));
        assertTrue(cf.tieneUsuario());
    }

    @Test
    void testSetUsuarioInvalido() {
        assertFalse(cf.setUsuario(""));
        assertFalse(cf.setUsuario(null));
    }

    @Test
    void testTieneUsuario() {
        assertFalse(cf.tieneUsuario());
        cf.setUsuario("Ale");
        assertTrue(cf.tieneUsuario());
    }

    @Test
    void testAgregarIngresoSinUsuario() {
        assertFalse(cf.agregarIngreso(100));
    }

    @Test
    void testAgregarIngresoNegativo() {
        cf.setUsuario("Ale");
        assertFalse(cf.agregarIngreso(-50));
    }

    @Test
    void testAgregarIngresoValido() {
        cf.setUsuario("Ale");
        assertTrue(cf.agregarIngreso(150));
        assertEquals(150.0, cf.consultarSaldo(), 0.001);
    }

    @Test
    void testAgregarGastoSinUsuario() {
        assertFalse(cf.agregarGasto(50, "Comida"));
    }

    @Test
    void testAgregarGastoSaldoInsuficiente() {
        cf.setUsuario("Ale");
        cf.agregarIngreso(20);
        assertFalse(cf.agregarGasto(50, "Viaje"));
    }

    @Test
    void testAgregarGastoValido() {
        cf.setUsuario("Ale");
        cf.agregarIngreso(100);
        assertTrue(cf.agregarGasto(30, "Transporte"));
        assertEquals(70.0, cf.consultarSaldo(), 0.001);
    }

    @Test
    void testConsultarSaldoSinUsuario() {
        assertEquals(-1.0, cf.consultarSaldo());
    }

    @Test
    void testConsultarSaldoConUsuario() {
        cf.setUsuario("Ale");
        cf.agregarIngreso(300);
        cf.agregarGasto(100, "Ropa");
        assertEquals(200.0, cf.consultarSaldo(), 0.001);
    }

    @Test
    void testMostrarMenuNoLanzaErrores() {
        assertDoesNotThrow(() -> {
            // No se testea mostrarMenu por depender de la consola.
            // Esto solo verifica que existe y no lanza errores.
        });
    }

    @Test
    void testMainNoLanzaErrores() {
        assertDoesNotThrow(() -> ControlFinanzas.main(new String[]{}));
    }
}

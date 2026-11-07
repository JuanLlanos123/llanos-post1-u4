// src/test/java/com/universidad/compras/notificacion/NotificacionEstadoTest.java
package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class NotificacionEstadoTest {

    @Test
    void cambiarEstadoDisparaLasTresReaccionesSinLanzarExcepcion() {
        Solicitud s = new Solicitud("S-020", "ana@udes.edu.co", 2500000, "SOFTWARE", "CC-100");
        PublicadorNotificacion mecanismo = new PublicadorNotificacion();
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        PrintStream salidaOriginal = System.out;

        try {
            System.setOut(new PrintStream(salida));
            assertDoesNotThrow(() ->
                mecanismo.cambiarEstado(s, "APROBADA", "Solicitud aprobada")
            );
        } finally {
            System.setOut(salidaOriginal);
        }

        String reacciones = salida.toString();
        assertTrue(reacciones.contains("[EMAIL -> ana@udes.edu.co]"));
        assertTrue(reacciones.contains("[DASHBOARD] Solicitud S-020 -> APROBADA"));
        assertTrue(reacciones.contains("[AUDITORIA] S-020 -> APROBADA"));
        assertEquals("APROBADA", s.getEstado());
    }

    @Test
    void agregarUnCuartoSuscriptorDePruebaNoRequiereModificarElMecanismo() {
        Solicitud s = new Solicitud("S-021", "ana@udes.edu.co", 1000, "SOFTWARE", "CC-100");
        PublicadorNotificacion mecanismo = new PublicadorNotificacion();
        StringBuilder estadoRecibido = new StringBuilder();
        mecanismo.agregarNotificacion((solicitud, estadoAnterior, detalle) ->
            estadoRecibido.append(estadoAnterior).append(" -> ").append(solicitud.getEstado())
        );

        assertDoesNotThrow(() -> mecanismo.cambiarEstado(s, "RECHAZADA", "Fuera de presupuesto"));
        assertEquals("PENDIENTE -> RECHAZADA", estadoRecibido.toString());
    }
}

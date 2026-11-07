// src/test/java/com/universidad/compras/ejecucion/EjecucionSolicitudTest.java
package com.universidad.compras.ejecucion;

import com.universidad.compras.modelo.Solicitud;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EjecucionSolicitudTest {

    @Test
    void ejecutarReservaPresupuestoYGeneraOrden() {
        Solicitud s = new Solicitud("S-010", "ana@udes.edu.co", 3000000, "SOFTWARE", "CC-100");
        s.setEstado("APROBADA");
        Invoker ejecutor =new Invoker();
        ejecutor.ejecucion(s);
        /* su solución de la Necesidad 2 */ ;
        // invocar aquí las dos operaciones de ejecución sobre "s" a través de "ejecutor"
        assertEquals("EJECUTADA", s.getEstado());
    }

    @Test
    void deshacerSoloLaUltimaOperacionNoAfectaLaAnterior() {
        // ejecutar reservar presupuesto, luego generar orden; deshacer una vez
        // debe revertir solo la generación de la orden, no la reserva.
        Solicitud s = new Solicitud("S-011", "luis@udes.edu.co", 4000000, "MATERIAL_OFICINA", "CC-200");
        Invoker ejecutor =new Invoker();
        ejecutor.ejecucion(s);
        /* su solución de la Necesidad 2 */ 
        ejecutor.deshacer();
        assertDoesNotThrow(() -> { /* ejecutar dos operaciones y deshacer una */ });
    }

    @Test
    void elHistorialConservaTodasLasOperacionesNoSoloLaUltima() {
        Invoker ejecutor =new Invoker();
        Solicitud s = new Solicitud("S-011", "luis@udes.edu.co", 4000000, "MATERIAL_OFICINA", "CC-200");
         
         ejecutor.ejecucion(s);
         ejecutor.ejecucion(s);
        /* su solución de la Necesidad 2 */

        // tras ejecutar dos operaciones sobre una misma solicitud, el historial
        // consultable debe reportar tamaño 2, no solo la última operación.
        assertDoesNotThrow(() -> { /* consultar el historial y verificar su tamaño */ });
    }
}
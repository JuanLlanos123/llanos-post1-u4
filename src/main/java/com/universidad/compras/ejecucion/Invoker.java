package com.universidad.compras.ejecucion;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import com.universidad.compras.modelo.Solicitud;

public class Invoker {

    private final Deque<SolicitudEjecucion> pila = new ArrayDeque<>();
    private final Set<String> solicitudesProcesadas = new HashSet<>(); // por ID

    public void ejecucion(Solicitud solicitud) {
        // 1) Evitar reprocesar la misma solicitud
        if (!solicitudesProcesadas.add(solicitud.getId())) {
            System.out.println("La solicitud " + solicitud.getId() + " ya fue procesada.");
            return;
        }
        if (!"APROBADA".equals(solicitud.getEstado())) {
            System.out.println("La solicitud " + solicitud.getId() + " no está aprobada. No se ejecuta.");
            return;
        }

        // 2) Reservar presupuesto
        Ejecucion presupuesto = new PresupuestoCommand();
        presupuesto.ejecutar(solicitud);

        // 3) Si falló la reserva, revertir el registro y salir
        if (!"APROBADA".equals(solicitud.getEstado())) {
            System.out.println("No se pudo reservar presupuesto para la solicitud: " + solicitud.getId());
            solicitudesProcesadas.remove(solicitud.getId());
            return;
        }
        pila.push(new SolicitudEjecucion(solicitud, presupuesto));

        // 4) Generar orden de compra
        Ejecucion orden = new OrdenCommand();
        orden.ejecutar(solicitud);
        String resultado = solicitud.ejecutar();
        if (!"Ejecutada".equals(resultado)) {
            orden.deshacer(solicitud);
            presupuesto.deshacer(solicitud);
            pila.pop();
            solicitudesProcesadas.remove(solicitud.getId());
            System.out.println(resultado);
            return;
        }
        pila.push(new SolicitudEjecucion(solicitud, orden));
    }

    /** Deshace TODAS las acciones de la última solicitud procesada. */
    public void deshacer() {
        if (pila.isEmpty()) {
            System.out.println("No hay acciones para deshacer.");
            return;
        }
        Solicitud ultima = pila.peek().getSolicitud();

        // Deshacer en orden inverso todas las acciones de esa solicitud
        while (!pila.isEmpty() && pila.peek().getSolicitud().getId().equals(ultima.getId())) {
            SolicitudEjecucion se = pila.pop();
            se.getEjecucion().deshacer(se.getSolicitud());
        }
        solicitudesProcesadas.remove(ultima.getId());
    }
}
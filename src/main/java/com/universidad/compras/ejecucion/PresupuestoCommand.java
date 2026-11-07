package com.universidad.compras.ejecucion;

import com.universidad.compras.modelo.Solicitud;

public class PresupuestoCommand implements Ejecucion {

    private final PresupuestoService presupuestoService = new PresupuestoService();

    @Override
    public void ejecutar(Solicitud solicitud) {
        boolean reservado = presupuestoService.reservar(
                solicitud.getCentroCosto(), solicitud.getMonto());
        if (reservado) {
            System.out.println("Presupuesto reservado para la solicitud: " + solicitud.getId());
        } else {
            System.out.println("No se pudo reservar presupuesto para: " + solicitud.getId());
            solicitud.setEstado("RECHAZADA");
        }
    }

    @Override
    public void deshacer(Solicitud solicitud) {
        presupuestoService.liberar(solicitud.getCentroCosto(), solicitud.getMonto());
        System.out.println("Reserva liberada para centro de costo: " + solicitud.getCentroCosto());
    }
}
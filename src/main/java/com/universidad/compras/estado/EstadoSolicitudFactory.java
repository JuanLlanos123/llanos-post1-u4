package com.universidad.compras.estado;

public final class EstadoSolicitudFactory {
    private EstadoSolicitudFactory() {}

    public static EstadoSolicitud crear(String nombre) {
        if ("APROBADA".equals(nombre)) {
            return new EstadoAprobada();
        }
        if ("EJECUTADA".equals(nombre)) {
            return new EstadoEjecutada();
        }
        if ("RECHAZADA".equals(nombre)) {
            return new EstadoRechazada();
        }
        if ("CANCELADA".equals(nombre)) {
            return new EstadoCancelada();
        }
        return new EstadoPendiente(nombre);
    }
}

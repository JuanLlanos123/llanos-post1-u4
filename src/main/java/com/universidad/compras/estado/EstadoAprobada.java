package com.universidad.compras.estado;

import com.universidad.compras.modelo.Solicitud;

public class EstadoAprobada implements EstadoSolicitud {
    @Override
    public String getNombre() {
        return "APROBADA";
    }

    @Override
    public String ejecutar(Solicitud solicitud) {
        solicitud.setEstado("EJECUTADA");
        return "Ejecutada";
    }
}

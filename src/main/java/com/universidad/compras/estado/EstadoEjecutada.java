package com.universidad.compras.estado;

import com.universidad.compras.modelo.Solicitud;

public class EstadoEjecutada implements EstadoSolicitud {
    @Override
    public String getNombre() {
        return "EJECUTADA";
    }

    @Override
    public String ejecutar(Solicitud solicitud) {
        return "Error: ya fue ejecutada";
    }
}

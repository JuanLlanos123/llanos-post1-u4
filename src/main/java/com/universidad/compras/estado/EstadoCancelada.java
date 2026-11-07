package com.universidad.compras.estado;

import com.universidad.compras.modelo.Solicitud;

public class EstadoCancelada implements EstadoSolicitud {
    @Override
    public String getNombre() {
        return "CANCELADA";
    }

    @Override
    public String ejecutar(Solicitud solicitud) {
        return "Error: no se puede ejecutar una solicitud " + getNombre();
    }
}

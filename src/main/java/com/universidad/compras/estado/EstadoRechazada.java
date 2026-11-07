package com.universidad.compras.estado;

import com.universidad.compras.modelo.Solicitud;

public class EstadoRechazada implements EstadoSolicitud {
    @Override
    public String getNombre() {
        return "RECHAZADA";
    }

    @Override
    public String ejecutar(Solicitud solicitud) {
        return "Error: no se puede ejecutar una solicitud " + getNombre();
    }
}

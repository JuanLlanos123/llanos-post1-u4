package com.universidad.compras.ejecucion;

import com.universidad.compras.modelo.Solicitud;

public class SolicitudEjecucion {
    private Solicitud solicitud;
    private Ejecucion ejecucion;
    public SolicitudEjecucion(Solicitud solicitud, Ejecucion ejecucion) {
        this.solicitud = solicitud;
        this.ejecucion = ejecucion;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }
    public Ejecucion getEjecucion() {
        return ejecucion;
    }
}

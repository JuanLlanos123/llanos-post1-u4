package com.universidad.compras.estado;

import java.util.Objects;

import com.universidad.compras.modelo.Solicitud;

public class ContextoSolicitud {
    private final Solicitud solicitud;

    public ContextoSolicitud(Solicitud solicitud) {
        this.solicitud = Objects.requireNonNull(solicitud, "solicitud no puede ser null");
    }

    public String ejecutar() {
        return solicitud.ejecutar();
    }
}

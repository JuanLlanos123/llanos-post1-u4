package com.universidad.compras.estado;

import com.universidad.compras.modelo.Solicitud;

public class EstadoPendiente implements EstadoSolicitud {
    private final String nombre;

    public EstadoPendiente(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String ejecutar(Solicitud solicitud) {
        return "Error: debe estar aprobada antes de ejecutarse";
    }
}

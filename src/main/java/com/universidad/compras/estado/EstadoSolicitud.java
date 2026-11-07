package com.universidad.compras.estado;

import com.universidad.compras.modelo.Solicitud;

public interface EstadoSolicitud {
    String getNombre();

    String ejecutar(Solicitud solicitud);
}

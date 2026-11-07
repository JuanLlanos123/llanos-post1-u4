package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;

public interface Notificacion {
    void actualizar(Solicitud solicitud, String estadoAnterior, String detalle);
}
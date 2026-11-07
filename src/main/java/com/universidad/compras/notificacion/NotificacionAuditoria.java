package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;

public class NotificacionAuditoria implements Notificacion {
    @Override
    public void actualizar(Solicitud solicitud, String estadoAnterior, String detalle) {
        String solicitudId = solicitud.getId();
        String estadoActual = solicitud.getEstado();
        ClientesNotificacion.registrarAuditoria(solicitudId, estadoActual, detalle);
    }
    
}

package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;

public class NotificacionDashboard implements Notificacion{

    @Override
    public void actualizar(Solicitud solicitud, String estadoAnterior, String detalle) {
        String solicitudId = solicitud.getId();
        String estadoActual = solicitud.getEstado();
        double monto = solicitud.getMonto();
        ClientesNotificacion.actualizarDashboardContabilidad(solicitudId, estadoActual, monto);
    }
    
}

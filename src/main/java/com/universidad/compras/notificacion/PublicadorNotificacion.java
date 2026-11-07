package com.universidad.compras.notificacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.universidad.compras.modelo.Solicitud;

public class PublicadorNotificacion {
    private final List<Notificacion> notificaciones = new ArrayList<>();

    public PublicadorNotificacion() {
        agregarNotificacion(new NotificadorCorreo());
        agregarNotificacion(new NotificacionDashboard());
        agregarNotificacion(new NotificacionAuditoria());
    }

    public void agregarNotificacion(Notificacion notificacion) {
        notificaciones.add(Objects.requireNonNull(notificacion, "notificacion no puede ser null"));
    }

    public void eliminarNotificacion(Notificacion notificacion) {
        notificaciones.remove(notificacion);
    }

    public void cambiarEstado(Solicitud solicitud, String nuevoEstado, String detalle) {
        Objects.requireNonNull(solicitud, "solicitud no puede ser null");
        String estadoAnterior = solicitud.getEstado();
        solicitud.setEstado(nuevoEstado);
        notificar(solicitud, estadoAnterior, detalle);
    }

    public void notificar(Solicitud solicitud, String estadoAnterior, String detalle) {
        for (Notificacion notificacion : new ArrayList<>(notificaciones)) {
            notificacion.actualizar(solicitud, estadoAnterior, detalle);
        }
    }
}

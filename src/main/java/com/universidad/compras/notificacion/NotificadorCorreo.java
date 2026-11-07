package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;

public class NotificadorCorreo implements Notificacion{
    @Override
    public void actualizar(Solicitud solicitud, String estadoAnterior, String detalle) {
        String destinatario = solicitud.getSolicitanteEmail();
        String asunto = "Actualización de solicitud: " + solicitud.getId();
        String cuerpo = "La solicitud ha cambiado de estado de " + estadoAnterior + " a " + solicitud.getEstado() + ". Detalle: " + detalle;
        ClientesNotificacion.enviarCorreo(destinatario, asunto, cuerpo);
    }     
    
}

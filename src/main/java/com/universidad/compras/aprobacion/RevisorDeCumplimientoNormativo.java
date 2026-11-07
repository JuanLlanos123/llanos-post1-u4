package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;

public class RevisorDeCumplimientoNormativo   extends ManejarSolicitud {

    public RevisorDeCumplimientoNormativo(ServicioAprobacion siguiente) {
        super(siguiente);
    }

    @Override
    public ResultadoAprobacion evaluar(Solicitud solicitud) {
        if(solicitud.getCategoria().equals("INTERNACIONAL"))
        return new ResultadoAprobacion(true, "Revisor de Cumplimiento Normativo", "Solicitud revisada y aprobada por el revisor de cumplimiento normativo.");
        else {
            return enviarSiguiente(solicitud);
        }
    }
   
}

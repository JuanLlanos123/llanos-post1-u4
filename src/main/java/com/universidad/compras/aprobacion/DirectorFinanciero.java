package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;

public class DirectorFinanciero extends ManejarSolicitud {

    public DirectorFinanciero(ServicioAprobacion siguiente) {
        super(siguiente);
    }

    @Override
    public ResultadoAprobacion evaluar(Solicitud solicitud) {
        if (solicitud.getMonto() <= 1000000000) {
            return new ResultadoAprobacion(true, "Director Financiero", "Solicitud aprobada por el director financiero.");
        } else {
            return enviarSiguiente(solicitud);
        }
    }
    
}

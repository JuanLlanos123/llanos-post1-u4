package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;

public class GerenteDeArea extends ManejarSolicitud {

    public GerenteDeArea(ServicioAprobacion siguiente) {
        super(siguiente);
    }

    @Override
    public ResultadoAprobacion evaluar(Solicitud solicitud) {
        if (solicitud.getMonto() <= 10000000) {
            return new ResultadoAprobacion(true, "Gerente de Área", "Solicitud aprobada por el gerente de área.");
        } else {
            return enviarSiguiente(solicitud);
        }
    }
    
}

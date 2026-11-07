package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;

public class Supervisor extends ManejarSolicitud {

    public Supervisor(ServicioAprobacion siguiente) {
        super(siguiente);
    }

    @Override
    public ResultadoAprobacion evaluar(Solicitud solicitud) {
        if (solicitud.getMonto() <= 2000000) {
            return new ResultadoAprobacion(true, "Supervisor de Área", "Solicitud aprobada por el supervisor.");
        } else {
            return enviarSiguiente(solicitud);
        }
    }
 }
    
    


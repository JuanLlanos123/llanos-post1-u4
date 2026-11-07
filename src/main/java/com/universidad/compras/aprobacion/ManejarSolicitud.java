package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;

public abstract class ManejarSolicitud implements ServicioAprobacion {


    protected ServicioAprobacion siguiente;

    public ManejarSolicitud(ServicioAprobacion siguiente) {
        this.siguiente = siguiente;
    }

   
    public ResultadoAprobacion enviarSiguiente(Solicitud solicitud){
        
        if (siguiente != null) {
            return siguiente.evaluar(solicitud);
    }
     return new ResultadoAprobacion(false, "N/A", "No hay un siguiente nivel de aprobación.");
}
    
    public abstract ResultadoAprobacion evaluar(Solicitud solicitud) ;
}
    

        
    
       
    


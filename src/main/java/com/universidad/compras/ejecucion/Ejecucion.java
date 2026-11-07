package com.universidad.compras.ejecucion;

import com.universidad.compras.modelo.Solicitud;

public interface Ejecucion {
    void ejecutar(Solicitud solicitud);
    void deshacer(Solicitud solicitud);
}

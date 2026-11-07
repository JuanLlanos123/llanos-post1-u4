package com.universidad.compras.ejecucion;

import com.universidad.compras.modelo.Solicitud;

public class OrdenCommand implements Ejecucion {

    private final OrdenCompraService ordenService = new OrdenCompraService();
    private final java.util.Map<String, String> ordenesPorSolicitud = new java.util.HashMap<>();

    @Override
    public void ejecutar(Solicitud solicitud) {
        String idOrden = ordenService.generar(solicitud.getId(), "Proveedor Ejemplo");
        ordenesPorSolicitud.put(solicitud.getId(), idOrden);
        System.out.println("Orden de compra " + idOrden + " generada para solicitud " + solicitud.getId());
    }

    @Override
    public void deshacer(Solicitud solicitud) {
        String idOrden = ordenesPorSolicitud.remove(solicitud.getId());
        if (idOrden != null) {
            ordenService.cancelar(idOrden);
            System.out.println("Orden de compra " + idOrden + " cancelada.");
        }
    }
}
package com.universidad.compras.modelo;

import com.universidad.compras.estado.EstadoSolicitud;
import com.universidad.compras.estado.EstadoSolicitudFactory;

// Conserva el contrato de estado como texto para los módulos existentes.
public class Solicitud {
    private final String id;
    private final String solicitanteEmail;
    private final double monto;
    private final String categoria;   // ej. "MATERIAL_OFICINA", "SOFTWARE", "INTERNACIONAL"
    private final String centroCosto;
    private EstadoSolicitud estado;   // PENDIENTE, EN_APROBACION, APROBADA, RECHAZADA, EJECUTADA, CANCELADA
    private String nivelResolutor;    // quién tomó la última decisión

    public Solicitud(String id, String solicitanteEmail, double monto, String categoria, String centroCosto) {
        this.id = id;
        this.solicitanteEmail = solicitanteEmail;
        this.monto = monto;
        this.categoria = categoria;
        this.centroCosto = centroCosto;
        this.estado = EstadoSolicitudFactory.crear("PENDIENTE");
    }

    public String getId()               { return id; }
    public String getSolicitanteEmail() { return solicitanteEmail; }
    public double getMonto()            { return monto; }
    public String getCategoria()        { return categoria; }
    public String getCentroCosto()      { return centroCosto; }
    public String getEstado()           { return estado.getNombre(); }
    public String getNivelResolutor()   { return nivelResolutor; }

    public void setEstado(String estado)              { this.estado = EstadoSolicitudFactory.crear(estado); }
    public void setNivelResolutor(String nivel)        { this.nivelResolutor = nivel; }

    public String ejecutar() {
        return estado.ejecutar(this);
    }

    @Override
    public String toString() {
        return "Solicitud{id='" + id + "', estado='" + getEstado() + "', monto=" + monto + "}";
    }
}
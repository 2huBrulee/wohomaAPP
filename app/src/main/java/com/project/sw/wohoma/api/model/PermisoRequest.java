package com.project.sw.wohoma.api.model;

public class PermisoRequest {

    public String razon;

    public String fechaInicial;

    public String fechaFinal;

    public int id_empleado;

    public PermisoRequest(int id_empleado, String razon, String fechaInicial, String fechaFinal){
        this.id_empleado = id_empleado;
        this.razon = razon;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }
}

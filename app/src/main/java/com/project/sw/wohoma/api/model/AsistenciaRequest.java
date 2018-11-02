package com.project.sw.wohoma.api.model;

public class AsistenciaRequest {

    private int id_empleado;

    private String asistenciaSalida;

    public AsistenciaRequest(int id_empleado){
        this.id_empleado = id_empleado;
    }

    public AsistenciaRequest(int id_empleado, String asistenciaSalida){
        this.id_empleado = id_empleado;
        this.asistenciaSalida = asistenciaSalida;
    }
}

package com.project.sw.wohoma.api.model;

import com.google.gson.annotations.SerializedName;

public class AsistenciaModel {

    @SerializedName("id_asistencia")
    private Integer idAsistencia;

    @SerializedName("fecha")
    private String fecha;

    @SerializedName("asistenciaEntrada")
    private String asistenciaEntrada;

    @SerializedName("asistenciaSalida")
    private String asistenciaSalida;

    @SerializedName("horasExtras")
    private String horasExtras;

    @SerializedName("id_empleado")
    private Integer idEmpleado;

    public Integer getIdAsistencia() {
        return idAsistencia;
    }

    public String getFecha() {
        return fecha;
    }

    public String getAsistenciaEntrada() {
        return asistenciaEntrada;
    }

    public String getAsistenciaSalida() {
        return asistenciaSalida;
    }

    public String getHorasExtras() {
        return horasExtras;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setAsistenciaEntrada(String asistenciaEntrada) {
        this.asistenciaEntrada = asistenciaEntrada;
    }

    public void setAsistenciaSalida(String asistenciaSalida) {
        this.asistenciaSalida = asistenciaSalida;
    }

    public void setHorasExtras(String horasExtras) {
        this.horasExtras = horasExtras;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}

package com.project.sw.wohoma.api.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by DIAZ QUIROZ on 5/06/2018.
 */

public class EntidadAsistencia{

    private String status = "ok";

    @SerializedName("idAsistencia")
    private Integer idAsistencia;

    @SerializedName("hEntrada")
    private Time hEntrada;

    @SerializedName("hSalida")
    private Time hSalida;

    @SerializedName("fecha")
    private Date fecha;

    @SerializedName("hExtras")
    private Integer hExtras;

    @SerializedName("idEmpleado")
    private Integer idempleado;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Time gethEntrada() {
        return hEntrada;
    }

    public void sethEntrada(String hEntrada) {
        this.hEntrada = java.sql.Time.valueOf(hEntrada);
    }

    public void sethEntrada(long hEntrada) {
        this.hEntrada = new Time(hEntrada*1000 - 3600000);
    }

    public Time gethSalida() {
        return hSalida;
    }

    public void sethSalida(String hSalida) {
        this.hSalida = java.sql.Time.valueOf(hSalida);
    }

    public void sethSalida(long hSalida) {
        this.hSalida = new Time(hSalida*1000 - 3600000);
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = new Date(fecha*1000);
    }

    public void setFecha(String fecha) {
        this.fecha = java.sql.Date.valueOf(fecha);
    }

    public Integer gethExtras() {
        return hExtras;
    }

    public void sethExtras(int hExtras) {
        this.hExtras = hExtras;
    }

    public Integer getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    @Override
    public String toString() {
        return "EntidadAsistencia{" +
                "status='" + status + '\'' +
                ", idAsistencia=" + idAsistencia +
                ", hEntrada=" + hEntrada +
                ", hSalida=" + hSalida +
                ", fecha=" + fecha +
                ", hExtras=" + hExtras +
                ", idempleado=" + idempleado +
                '}';
    }
}

package com.project.sw.wohoma.api.model;

public class TiendaRequest {

    private String nombreTienda;

    private String direccion;

    private String referencia;

    private int telefono;

    private double latitud0;

    private double longuitud0;

    private double latitud1;

    private double longuitud1;

    private double latitud2;

    private double longuitud2;

    private double latitud3;

    private double longuitud3;

    public TiendaRequest(){
    }

    public TiendaRequest(String nombreTienda, String direccion, String referencia,
                         int telefono, double latitud0, double longuitud0, double latitud1,
                         double longuitud1, double latitud2, double longuitud2, double latitud3,
                         double longuitud3) {
        this.nombreTienda = nombreTienda;
        this.direccion = direccion;
        this.referencia = referencia;
        this.telefono = telefono;
        this.latitud0 = latitud0;
        this.longuitud0 = longuitud0;
        this.latitud1 = latitud1;
        this.longuitud1 = longuitud1;
        this.latitud2 = latitud2;
        this.longuitud2 = longuitud2;
        this.latitud3 = latitud3;
        this.longuitud3 = longuitud3;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getReferencia() {
        return referencia;
    }

    public int getTelefono() {
        return telefono;
    }

    public double getLatitud0() {
        return latitud0;
    }

    public double getLonguitud0() {
        return longuitud0;
    }

    public double getLatitud1() {
        return latitud1;
    }

    public double getLonguitud1() {
        return longuitud1;
    }

    public double getLatitud2() {
        return latitud2;
    }

    public double getLonguitud2() {
        return longuitud2;
    }

    public double getLatitud3() {
        return latitud3;
    }

    public double getLonguitud3() {
        return longuitud3;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setLatitud0(double latitud0) {
        this.latitud0 = latitud0;
    }

    public void setLonguitud0(double longuitud0) {
        this.longuitud0 = longuitud0;
    }

    public void setLatitud1(double latitud1) {
        this.latitud1 = latitud1;
    }

    public void setLonguitud1(double longuitud1) {
        this.longuitud1 = longuitud1;
    }

    public void setLatitud2(double latitud2) {
        this.latitud2 = latitud2;
    }

    public void setLonguitud2(double longuitud2) {
        this.longuitud2 = longuitud2;
    }

    public void setLatitud3(double latitud3) {
        this.latitud3 = latitud3;
    }

    public void setLonguitud3(double longuitud3) {
        this.longuitud3 = longuitud3;
    }
}

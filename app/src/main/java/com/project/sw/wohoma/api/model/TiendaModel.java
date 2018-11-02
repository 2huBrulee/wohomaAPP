package com.project.sw.wohoma.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TiendaModel implements Serializable {

    @SerializedName("id_tienda")
    private int idTienda;

    @SerializedName("nombreTienda")
    private String nombreTienda;

    @SerializedName("direccion")
    private String direccion;

    @SerializedName("referencia")
    private String referencia;

    @SerializedName("telefono")
    private int telefono;

    @SerializedName("latitud0")
    private String latitud0;

    @SerializedName("longuitud0")
    private String longuitud0;

    @SerializedName("latitud1")
    private String latitud1;

    @SerializedName("longuitud1")
    private String longuitud1;

    @SerializedName("latitud2")
    private String latitud2;

    @SerializedName("longuitud2")
    private String longuitud2;

    @SerializedName("latitud3")
    private String latitud3;

    @SerializedName("longuitud3")
    private String longuitud3;

    public int getIdTienda() {
        return idTienda;
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

    public String getLatitud0() {
        return latitud0;
    }

    public String getLonguitud0() {
        return longuitud0;
    }

    public String getLatitud1() {
        return latitud1;
    }

    public String getLonguitud1() {
        return longuitud1;
    }

    public String getLatitud2() {
        return latitud2;
    }

    public String getLonguitud2() {
        return longuitud2;
    }

    public String getLatitud3() {
        return latitud3;
    }

    public String getLonguitud3() {
        return longuitud3;
    }
}

package com.project.sw.wohoma.api.model;

import com.google.gson.annotations.SerializedName;

public class RolModel {

    @SerializedName("id_rol")
    private Integer idRol;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("descripcion")
    private String descripcion;

    public Integer getIdRol() {
        return idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

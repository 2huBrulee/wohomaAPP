package com.project.sw.wohoma.api.model;

import com.google.gson.annotations.SerializedName;

public class PermisoModel {

    @SerializedName("id_permiso")
    public int idPermiso;

    @SerializedName("razon")
    public String razon;

    @SerializedName("fechaInicial")
    public String fechaInicial;

    @SerializedName("fechaFinal")
    public String fechaFinal;

    @SerializedName("estado")
    public String estado;

    @SerializedName("id_empleado")
    public int idEmpleado;

    public int getIdPermiso() {
        return idPermiso;
    }

    public String getRazon() {
        return razon;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public String getEstado() {
        return estado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }
}

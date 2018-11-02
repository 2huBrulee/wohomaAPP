package com.project.sw.wohoma.api.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("empleado")
    private EmpleadoModel empleado;

    @SerializedName("msg")
    private String msg;

    @SerializedName("status")
    private Integer status;

    public EmpleadoModel getEmpleado() {
        return empleado;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getStatus() {
        return status;
    }
}

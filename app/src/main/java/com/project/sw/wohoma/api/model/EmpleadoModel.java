package com.project.sw.wohoma.api.model;

import com.google.gson.annotations.SerializedName;

public class EmpleadoModel {
    @SerializedName("id_empleado")
    private Integer idEmpleado;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("apellidos")
    private String apellidos;

    @SerializedName("dni")
    private Integer dni;

    @SerializedName("telefono")
    private Integer telefono;

    @SerializedName("celular")
    private Integer celular;

    @SerializedName("direccion")
    private String direccion;

    @SerializedName("sueldo")
    private String sueldo;

    @SerializedName("usuario")
    private String usuario;

    @SerializedName("password")
    private String password;

    @SerializedName("id_rol")
    private RolModel idRol;

    @SerializedName("id_tienda")
    private TiendaModel idTienda;

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Integer getDni() {
        return dni;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public Integer getCelular() {
        return celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getSueldo() {
        return sueldo;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public RolModel getIdRol() {
        return idRol;
    }

    public TiendaModel getIdTienda() {
        return idTienda;
    }
}

package com.project.sw.wohoma.api.model;

public class LoginRequest {

    public String usuario;
    public String password;

    public LoginRequest(String usuario, String password){
        this.usuario = usuario;
        this.password = password;
    }

}

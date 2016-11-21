package com.proyecto.aplicada.conectados;

/**
 * Created by Jry25 on 11/11/2016.
 */

public class Usuario {

    String usuario, nombreCompleto, carnet, correo, password;
    int estado;

    public Usuario(String usuario, String password, String nombreCompleto, String carnet, String correo,int estado){
        this.correo=correo;
        this.password=password;
        this.nombreCompleto=nombreCompleto;
        this.usuario=usuario;
        this.carnet=carnet;
        this.estado=estado;

    }
}

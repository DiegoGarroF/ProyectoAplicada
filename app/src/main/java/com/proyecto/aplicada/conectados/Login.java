package com.proyecto.aplicada.conectados;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Luis on 18/11/2016.
 */

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button btnInciarSesion;
    private Button btnCancelar;

    private EditText txtUsuario;
    private EditText txtPassword;
    SqlConexion conexion;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnCancelar=(Button)findViewById(R.id.btnCancelarLogin);
        btnInciarSesion=(Button)findViewById(R.id.btnLogin);
        txtPassword=(EditText)findViewById(R.id.txtPasswordLogin);
        txtUsuario=(EditText)findViewById(R.id.txtUsuario);

        btnCancelar.setOnClickListener(this);
        btnInciarSesion.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnLogin:
                String usuario = txtUsuario.getText().toString();
                String password = txtPassword.getText().toString();

                String type="Login";
                if(!usuario.trim().equalsIgnoreCase("")&&!password.trim().equalsIgnoreCase("")) {
                    ConexionSql conexionSql = new ConexionSql(this);
                    conexionSql.execute(type, usuario, password);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Complete los datos",Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.btnCancelarLogin:
                Intent intent = new Intent(Login.this,Inicio.class);
                startActivity(intent);
                break;
        }
    }
}

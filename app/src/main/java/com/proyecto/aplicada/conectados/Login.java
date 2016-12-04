package com.proyecto.aplicada.conectados;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    TextView recuperarPw;
    public static  String NOMBREUSUARIO="";
    public static  String CONTRASEÑA="";
    public static String datos="";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnCancelar=(Button)findViewById(R.id.btnCancelarLogin);
        btnInciarSesion=(Button)findViewById(R.id.btnLogin);
        txtPassword=(EditText)findViewById(R.id.txtPasswordLogin);
        txtUsuario=(EditText)findViewById(R.id.txtUsuario);

        btnCancelar.setOnClickListener(this);
        btnInciarSesion.setOnClickListener(this);

        recuperarPw = (TextView)findViewById(R.id.idviewContraseñaOlv);
        recuperarPw.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnLogin:
                String usuario = txtUsuario.getText().toString();
                String password = txtPassword.getText().toString();
                NOMBREUSUARIO=txtUsuario.getText().toString();
                CONTRASEÑA=txtPassword.getText().toString();
                String type="Login";
                if(!usuario.trim().equalsIgnoreCase("")&&!password.trim().equalsIgnoreCase("")) {
                    ConexionSql conexionSql = new ConexionSql(this);
                    conexionSql.execute(type, usuario, password);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Complete los datos",Toast.LENGTH_LONG).show();
                }
                txtUsuario.setText("");
                txtPassword.setText("");
                break;

            case R.id.btnCancelarLogin:
                Intent intent = new Intent(Login.this,Inicio.class);
                startActivity(intent);
                break;

            case R.id.idviewContraseñaOlv:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Se procederá a verificar su identidad")
                                .setTitle("Recuperando contraseña")
                            .setCancelable(false)
                            .setNeutralButton("Aceptar",
                                    new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                        });
                AlertDialog alert = builder.create();
                alert.show();
                break;

        }
    }
}

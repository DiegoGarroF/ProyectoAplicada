package com.proyecto.aplicada.conectados;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegistrarme;
    private Button btnCancelar;
    private SqlConexion conexion;

    private EditText txtNombreCompleto,txtUsuario,txtPassword,txtCarnet,txtCorreo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnRegistrarme=(Button)findViewById(R.id.idBtnRegistrarme);
        btnCancelar=(Button)findViewById(R.id.idBtnCancelar);

        txtNombreCompleto=(EditText)findViewById(R.id.txtNombreCompleto);
        txtUsuario=(EditText)findViewById(R.id.txtNombreUsuario);
        txtPassword=(EditText)findViewById(R.id.txtPassword);
        txtCarnet=(EditText)findViewById(R.id.txtCarnet);
        txtCorreo=(EditText)findViewById(R.id.txtCorreoDe);

        btnCancelar.setOnClickListener(this);
        btnRegistrarme.setOnClickListener(this);

        conexion= new SqlConexion(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.idBtnRegistrarme:
                System.out.println("Entro");
                String type="Registrar";
                String usuario = txtUsuario.getText().toString(); ;
                String password = txtPassword.getText().toString();;
                String nombre=txtNombreCompleto.getText().toString();
                String carnet=txtCarnet.getText().toString();;
                String correo=txtCorreo.getText().toString();;
                ConexionSql conexionSql = new ConexionSql(this);
                conexionSql.execute(type, usuario, password,nombre,carnet,correo);

                break;
            case R.id.idBtnCancelar:
                Intent intent = new Intent(Registro.this,Inicio.class);
                startActivity(intent);
                break;

        }
    }
}

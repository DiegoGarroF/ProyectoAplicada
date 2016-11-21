package com.proyecto.aplicada.conectados;



import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Inicio extends AppCompatActivity implements View.OnClickListener{

    TextView recuperarPw;
    Button btnRegistrarse;
    Button btnIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        recuperarPw = (TextView)findViewById(R.id.idviewContraseñaOlv);
        recuperarPw.setOnClickListener(this);
        btnRegistrarse=(Button)findViewById(R.id.idBtnRegistrarse);
        btnRegistrarse.setOnClickListener(this);

        btnIniciarSesion=(Button)findViewById(R.id.idBtnIniciarSesion);
        btnIniciarSesion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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

            case R.id.idBtnRegistrarse:
                Intent intent = new Intent(Inicio.this,Registro.class);
                startActivity(intent);
                break;

            case R.id.idBtnIniciarSesion:
                Intent intent2 = new Intent(Inicio.this,Login.class);
                startActivity(intent2);
                break;
        }

    }
}

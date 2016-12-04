package com.proyecto.aplicada.conectados;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActivityMsjNuevo extends AppCompatActivity implements View.OnClickListener{

    private EditText txtFecha, txtDesc;
    TextView txtUsuarioPara;
    SqlConexion conexion;
    private Spinner para;
    Date d = new Date();
    Button btnEnviarMsj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_msj_nuevo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnEnviarMsj = (Button) findViewById(R.id.btn_agregarM);
        btnEnviarMsj.setOnClickListener(this);

        txtFecha = (EditText) findViewById(R.id.edit_txt_fecha);
        txtDesc = (EditText) findViewById(R.id.edit_txt_descripcion);
        txtUsuarioPara=(TextView) findViewById(R.id.fecha_label);
        para = (Spinner) findViewById(R.id.spinner_para);
        conexion = new SqlConexion(this);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.destinatarios, android.R.layout.simple_spinner_dropdown_item);
        //Añadimos el layout para el menú
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Le indicamos al spinner el adaptador a usar
        para.setAdapter(adapter);
        txtFecha.setText(getDateTime());
    }

    private String getDateTime() {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }



    @Override
    public void onClick(View v) {

    switch (v.getId()){
        case R.id.btn_agregarM:
            String destinatario="";

            if(para.getSelectedItem().toString().equalsIgnoreCase(("Biblioteca"))){
                destinatario="admBiblioteca";
            }else{
                if(para.getSelectedItem().toString().equalsIgnoreCase(("Oficina de Becas"))){
                    destinatario="admBecas";
                }else{
                    destinatario="admRegistro";
                }

            }

            ConexionSql conexionSql = new ConexionSql(this);
            //Ojo aquí xq si no se pasa el intent entonces no obtendrá el usuario
            conexionSql.execute("insertarMsj",getIntent().getExtras().getString("usuario"),destinatario,txtDesc.getText().toString(),"0");

            break;
    }
    }
}

